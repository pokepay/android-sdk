package jp.pokepay.pokepaylib.Pokeregi;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import jp.pokepay.pokepaylib.AES128;
import jp.pokepay.pokepaylib.ProcessingError;

import static android.os.SystemClock.sleep;

public class BLEController {

    public enum ResultCode {
        NOT_STARTED_YET,
        PROCESSING,
        DONE,
        ERROR,
        TIMEOUT,
    };

    public class Result {
        public ResultCode code;
        public String data;
        public ProcessingError err;
        Result(ResultCode c, String d, ProcessingError e) {
            code = c;
            data = d;
            err = e;
        }
    };

    final static private String TAG = "Pokeregi.BLE";

    private static final String serviceUUIDPrefix = "3c46a55f-5890-0689-9025-e75f";
    private static final String aesInitVector = "F0EE1BC8016A6335";
    private static final String rxCharId = "0020";
    private static final String txCharId = "0040";

    private Context context;
    private String serviceUUID;
    private AES128 aes;
    private BluetoothManager mBleManager;
    private BluetoothLeScanner mBleScanner;
    private BluetoothAdapter mBleAdapter;
    private BluetoothGatt mBleGatt;
    private BluetoothGattCharacteristic rxChar;
    private BluetoothGattCharacteristic txChar;

    private ArrayList<byte[]> jwtChunks = new ArrayList<byte[]>();

    private Boolean scanned = false;

    public Boolean resetScanned() {
        synchronized (scanned) {
            scanned = false;
            return new Boolean(scanned);
        }
    }

    private Result result = new Result(ResultCode.NOT_STARTED_YET, null, null);

    private void setResult(Result r) {
        synchronized(result) {
            result.code = r.code;
            result.data = r.data;
            result.err = r.err;
        }
    }

    private Result getResult() {
        synchronized(result) {
            return new Result(result.code, result.data, result.err);
        }
    }

    private Result waitResult(long timeoutMs) {
        final int tickMs = 10;
        long passedMs = 0;
        while (true) {
            final Result r = getResult();
            if (r.code != ResultCode.PROCESSING) {
                return r;
            }
            sleep(tickMs);
            passedMs += tickMs;
            if (passedMs > timeoutMs) {
                return new Result(ResultCode.TIMEOUT, null, new ProcessingError("BLE timeout operation"));
            }
        }
    }

    private String getUUID(String token) {
        String uuid = serviceUUIDPrefix;
        char[] tokenArray = token.substring(0, 8).toCharArray();
        for(int i=0; i < tokenArray.length-1; i++){
            int c = ((int)tokenArray[i]) % 0x0F;
            String hex = Integer.toHexString(c);
            uuid += hex;
        }
        uuid += "0";
        return uuid;
    }

    public BLEController(String token, Context context) {
        this.context = context;
        aes = new AES128(token.substring(token.length()-16), aesInitVector);
        serviceUUID = getUUID(token);
        setResult(new Result(ResultCode.NOT_STARTED_YET, null, null));
    }

    public void connect(long timeoutMs) throws ProcessingError {
        // Log.d(TAG, "connect");
        if (getResult().code == ResultCode.PROCESSING) {
            throw new ProcessingError("BLE running other operation");
        }
        if (mBleManager == null && mBleAdapter == null && mBleScanner == null) {
            mBleManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
            mBleAdapter = mBleManager.getAdapter();
            mBleScanner = mBleAdapter.getBluetoothLeScanner();
        }
        if (mBleAdapter == null || !mBleAdapter.isEnabled()) {
            throw new ProcessingError("BLE is not enabled");
        }
        final ScanFilter scanFilter = new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(serviceUUID)).build();
        final ArrayList scanFilters = new ArrayList(){{ add(scanFilter); }};
        final ScanSettings scanSettings = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY).build();
        setResult(new Result(ResultCode.PROCESSING, null, null));
        mBleScanner.startScan(scanFilters, scanSettings, scanCallback);
        Result result = waitResult(timeoutMs);
        if (result.code == ResultCode.ERROR || result.code == ResultCode.TIMEOUT) {
            disconnect();
            throw result.err;
        }
    }

    public void disconnect() {
        if (mBleGatt != null) {
            mBleGatt.close();
            mBleGatt = null;
        }
        if (mBleScanner != null) {
            mBleScanner.stopScan(scanCallback);
            resetScanned();
        }
        jwtChunks.clear();
        setResult(new Result(ResultCode.NOT_STARTED_YET, null, null));
    }

    public String read(long timeoutMs) throws ProcessingError {
        if (getResult().code == ResultCode.PROCESSING) {
            throw new ProcessingError("BLE running other operation");
        }
        if (mBleGatt == null || rxChar == null) {
            throw new ProcessingError("BLE is not connected yet");
        }
        setResult(new Result(ResultCode.PROCESSING, null, null));
        jwtChunks.clear();
        mBleGatt.readCharacteristic(rxChar);
        final Result result = waitResult(timeoutMs);
        // Log.d(TAG, "read result = " + result.code);
        if (result.code == ResultCode.ERROR || result.code == ResultCode.TIMEOUT) {
            disconnect();
            throw result.err;
        }
        return result.data;
    }

    public void write(String jwt, long timeoutMs) throws ProcessingError {
        if (getResult().code == ResultCode.PROCESSING) {
            throw new ProcessingError("BLE running other operation");
        }
        if (mBleGatt == null || txChar == null) {
            throw new ProcessingError("BLE is not connected yet");
        }
        setResult(new Result(ResultCode.PROCESSING, null, null));
        jwtChunks.clear();
        final byte[] jwtRaw = aes.encode(jwt);
        int i = 0;
        do {
            final byte[] chunk = Arrays.copyOfRange(jwtRaw, i, Math.min(i+500, jwtRaw.length));
            jwtChunks.add(chunk);
            i += 500;
        } while (i <= jwtRaw.length);
        final byte[] chunk = jwtChunks.remove(0);
        txChar.setValue(chunk);
        mBleGatt.writeCharacteristic(txChar);
        final Result result = waitResult(timeoutMs);
        if (result.code == ResultCode.ERROR || result.code == ResultCode.TIMEOUT) {
            disconnect();
            throw result.err;
        }
    }

    private final ScanCallback scanCallback = new ScanCallback() {

        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            mBleScanner.stopScan(scanCallback);
            synchronized (scanned) {
                if (scanned) return;
                scanned = true;
                result.getDevice().connectGatt(context.getApplicationContext(), false, mGattCallback);
            }
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE scan failed code(" + errorCode + ")")));
        }
    };

    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {

        private void refreshGatt(BluetoothGatt gatt) {
            try {
                final Method refresh = gatt.getClass().getMethod("refresh");
                if (refresh == null) {
                    Log.e(TAG,"Could not find function BluetoothGatt.refresh()");
                }
                final Boolean success = (Boolean) refresh.invoke(gatt);
                if (!success) Log.e(TAG, "BluetoothGatt.refresh() returned false");
            } catch (Exception e) {
                Log.e(TAG, "Could not call function BluetoothGatt.refresh()");
            }
        }

        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                mBleGatt = gatt;
                if (gatt.requestMtu(512)) {
                    // Log.d(TAG, "Requested MTU successfully");
                } else {
                    setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE failed to request MTU")));
                }
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                if (mBleGatt != null) {
                    mBleGatt.close();
                    mBleGatt = null;
                    setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE unexpected disconnection")));
                }
            }
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
            if (gatt != null && status == BluetoothGatt.GATT_SUCCESS) {
                refreshGatt(gatt);
                if (gatt.discoverServices()) {
                    // Log.d(TAG, "Started discovering services");
                } else {
                    setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE failed to request discover service")));
                }
            } else {
                setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE failed to change MTU")));
            }
        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                BluetoothGattService service = gatt.getService(UUID.fromString(serviceUUID));
                if (service != null) {
                    final ArrayList<BluetoothGattCharacteristic> chars = (ArrayList<BluetoothGattCharacteristic>) service.getCharacteristics();
                    rxChar = null;
                    txChar = null;
                    for (int i = 0; i < chars.size(); i++) {
                        final BluetoothGattCharacteristic ch = chars.get(i);
                        final String uuidShort = ch.getUuid().toString().substring(4, 8);
                        if (uuidShort.equals(rxCharId)) {
                            rxChar = ch;
                        } else if (uuidShort.equals(txCharId)) {
                            txChar = ch;
                        }
                    }
                    if (rxChar == null) {
                        setResult(new Result(ResultCode.ERROR, null, new ProcessingError("Characteristic for rx '0020' is not found")));
                        return;
                    }
                    if (txChar == null) {
                        setResult(new Result(ResultCode.ERROR, null, new ProcessingError("Characteristic for tx '0040' is not found")));
                        return;
                    }
                    // DONE of connect();
                    // Log.d(TAG, "DONE of connect()");
                    setResult(new Result(ResultCode.DONE, null, null));
                } else {
                    setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE failed serviceUUID not matched")));
                }
            } else {
                setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE failed to discover service")));
            }
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            // Log.d(TAG, "onRead");
            super.onCharacteristicRead(gatt, characteristic, status);
            if (BluetoothGatt.GATT_SUCCESS != status) {
                setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE characteristic read failed")));
                return;
            }
            final byte[] chunk = characteristic.getValue();
            jwtChunks.add(chunk);
            if (chunk.length < 500) {
                int size = 0;
                for (byte[] ch : jwtChunks) {
                    size += ch.length;
                }
                byte[] jwtRaw = new byte[size];
                int filled = 0;
                for (byte[] ch : jwtChunks) {
                    for (int i = 0; i < ch.length; i++, filled++) {
                        jwtRaw[filled] = ch[i];
                    }
                }
                jwtChunks.clear();
                String jwt;
                try {
                    jwt = aes.decode(jwtRaw);
                } catch (ProcessingError err) {
                    setResult(new Result(ResultCode.ERROR, null, err));
                    return;
                }
                // DONE of read()
                // Log.d(TAG, "DONE of read()");
                setResult(new Result(ResultCode.DONE, jwt, null));
            } else {
                gatt.readCharacteristic(rxChar);
            }
        }

        @Override
        public void  onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            // Log.d(TAG, "onWrite");
            super.onCharacteristicWrite(gatt, characteristic, status);
            switch(status) {
                case BluetoothGatt.GATT_SUCCESS:
                    if (jwtChunks.size() > 0) {
                        final byte[] chunk = jwtChunks.remove(0);
                        txChar.setValue(chunk);
                        gatt.writeCharacteristic(txChar);
                    } else {
                        // DONE of write()
                        // Log.d(TAG, "DONE of write()");
                        setResult(new Result(ResultCode.DONE, null, null));
                    }
                    break;
                case BluetoothGatt.GATT_WRITE_NOT_PERMITTED:
                    setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE characteristic write not permitted")));
                    break;
                case BluetoothGatt.GATT_REQUEST_NOT_SUPPORTED:
                    setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE characteristic write not supported")));
                    break;
                case BluetoothGatt.GATT_FAILURE:
                    setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE characteristic write failure")));
                    break;
                case BluetoothGatt.GATT_INSUFFICIENT_AUTHENTICATION:
                    setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE characteristic write insufficient authenrication")));
                    break;
                case BluetoothGatt.GATT_INSUFFICIENT_ENCRYPTION:
                    setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE characteristic write insufficient encryption")));
                    break;
                case BluetoothGatt.GATT_INVALID_OFFSET:
                    setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE characteristic write invalid offset")));
                    break;
                default:
                    setResult(new Result(ResultCode.ERROR, null, new ProcessingError("BLE characteristic write unknown error")));
            }
        }
    };
}
