package jp.pokepay.pokepaylib.Pokeregi;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
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
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithJwt;
import jp.pokepay.pokepaylib.Responses.JwtResult;

import static android.content.ContentValues.TAG;
import static java.lang.Thread.sleep;

public class BLEController {
    private String serviceUUID = "3c46a55f-5890-0689-9025-e75f";
    private String accessToken;
    private String bleResult = "wait";
    private Context context;
    private BluetoothLeScanner mBleScanner;
    private BluetoothManager mBleManager;
    private BluetoothAdapter mBleAdapter;
    private BluetoothGatt mBleGatt;
    private Boolean isScanned = false;
    private ArrayList<BluetoothGattCharacteristic> arrayList;
    private int arrayIdx = 0;
    private byte[] readJwt;
    private byte[] writeJwt;
    private int writeIdx = 0;

    private String aesSecretKey;
    final private String aesInitVector = "F0EE1BC8016A6335";

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public BLEController(String uuid, String accessToken, Context context){
        this.context = context;
        this.accessToken = accessToken;
        readJwt = new byte[0];
        String code = uuid;
        aesSecretKey = code.substring(code.length()-16, code.length());
        char[] codeArray = code.substring(0, 8).toCharArray();
        for(int i=0;i<codeArray.length-1;i++){
            int c = (int)codeArray[i];
            c %= 0x0f;
            String hex = Integer.toHexString(c);
            serviceUUID += hex;
        }
        serviceUUID += "0";
        mBleManager = (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        mBleAdapter = mBleManager.getAdapter();
        mBleScanner = mBleAdapter.getBluetoothLeScanner();
        ScanFilter scanFilter = new ScanFilter.Builder().setServiceUuid(ParcelUuid.fromString(serviceUUID)).build();
        ArrayList scanFilterList = new ArrayList();
        scanFilterList.add(scanFilter);
        ScanSettings scanSettings = new ScanSettings.Builder().setScanMode(ScanSettings.SCAN_MODE_BALANCED).build();

        mBleScanner.startScan( scanFilterList, scanSettings, scanCallback);
    }

    public String getResult(){
        while(bleResult.equals("wait")){
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return bleResult;
    }


    private final BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState)
        {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                if (gatt.requestMtu(512)) {
                    Log.d(TAG, "Requested MTU successfully");
                } else {
                    Log.d(TAG, "Failed to request MTU");
                }
                //gatt.discoverServices();
            } else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                if (mBleGatt != null)
                {
                    mBleGatt.close();
                    mBleGatt = null;
                }
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            // Exchange MTU Requestが完了してからサービスの検出を開始する
            if (gatt != null) {
                if (gatt.discoverServices()) {
                    Log.d(TAG, "Started discovering services");
                } else {
                    Log.d(TAG, "Failed to start discovering services");
                }
            }
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status){
            if(isScanned){
                return;
            }
            if (status == BluetoothGatt.GATT_SUCCESS) {
                isScanned = true;

                BluetoothGattService service = gatt.getService(UUID.fromString(serviceUUID));
                if (service != null) {
                    arrayList = (ArrayList<BluetoothGattCharacteristic>) service.getCharacteristics();
                    for(int i=0;i<arrayList.size();i++){
                        System.out.println(i + ":" + arrayList.get(i).getUuid());
                    }
                    gatt.readCharacteristic(arrayList.get(arrayIdx));
                }
            }
        }


        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
            if( BluetoothGatt.GATT_SUCCESS != status ){
                return;
            }
            System.out.println(characteristic.getUuid().toString());
            final byte[] tmpBytes = characteristic.getValue();
            System.out.println(Arrays.toString(tmpBytes) + ", " + tmpBytes.length + ", " + Arrays.toString(readJwt) + ", " + readJwt.length);
            byte[] tmp = readJwt.clone();
            readJwt = new byte[tmp.length + tmpBytes.length];
            for(int i=0;i<tmp.length;i++){
                readJwt[i] = tmp[i];
            }
            for(int i=0;i<tmpBytes.length;i++){
                readJwt[tmp.length+i] = tmpBytes[i];
            }
            if(tmpBytes.length < 500){
                arrayIdx++;
                String jwt = new String(decodeAES128(readJwt), StandardCharsets.UTF_8);
                CreateTransactionWithJwt createTransactionWithJwt = new CreateTransactionWithJwt(jwt, null);
                JwtResult jwtResult = createTransactionWithJwt.send(accessToken);
                String result = jwtResult.data;
                System.out.println(result);
                if(result == null){
                    arrayList.get(arrayIdx).setValue(encodeAES128("NG".getBytes()));
                    System.out.println("NG");
                }
                else {
                    writeJwt = encodeAES128(result.getBytes());
                    int len = writeJwt.length - writeIdx*500;
                    if(len >= 500){
                        len = 500;
                    }
                    byte[] value = new byte[len];
                    for(int i=0;i<len;i++){
                        value[i] = writeJwt[i];
                    }
                    writeIdx++;
                    arrayList.get(arrayIdx).setValue(value);
                    //arrayList.get(arrayIdx).setValue(writeJwt);
                }
                System.out.println(writeJwt.length);
                System.out.println(arrayList.get(arrayIdx).getUuid());
                gatt.writeCharacteristic(arrayList.get(arrayIdx));
            }
            else{
                gatt.readCharacteristic(arrayList.get(arrayIdx));
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void  onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            String TAG = "ScannerActivity";
            switch(status) {
                case BluetoothGatt.GATT_SUCCESS:
                    Log.e(TAG, "onCharacteristicWrite: GATT_SUCCESS");
                    int len = writeJwt.length - writeIdx*500;
                    System.out.println(writeJwt.length + ", " + len + ", " + writeIdx);
                    if(len < 0){
                        if(bleResult == "SUCCESS"){
                            break;
                        }
                        byte[] z = new byte[0];
                        arrayList.get(arrayIdx).setValue(z);
                        gatt.writeCharacteristic(arrayList.get(arrayIdx));
                        bleResult = "SUCCESS";
                        break;
                    }
                    if(len >= 500){
                        len = 500;
                    }
                    byte[] value = new byte[len];
                    for(int i=0;i<len;i++){
                        value[i] = writeJwt[i+writeIdx*500];
                    }
                    arrayList.get(arrayIdx).setValue(value);
                    writeIdx++;
                    gatt.writeCharacteristic(arrayList.get(arrayIdx));

                    break;
                case BluetoothGatt.GATT_WRITE_NOT_PERMITTED:
                    Log.e(TAG, "onCharacteristicWrite: GATT_WRITE_NOT_PERMITTED");
                    bleResult = "NOT PERMITTED";
                    break;

                case BluetoothGatt.GATT_REQUEST_NOT_SUPPORTED:
                    Log.e(TAG, "onCharacteristicWrite: GATT_REQUEST_NOT_SUPPORTED");
                    bleResult = "NOT SUPPORTED";
                    break;

                case BluetoothGatt.GATT_FAILURE:
                    Log.e(TAG, "onCharacteristicWrite: GATT_FAILURE");
                    bleResult = "NOT FAILURE";
                    break;

                case BluetoothGatt.GATT_INSUFFICIENT_AUTHENTICATION:
                    break;

                case BluetoothGatt.GATT_INSUFFICIENT_ENCRYPTION:
                    break;

                case BluetoothGatt.GATT_INVALID_OFFSET:
                    break;
            }
        }
    };

    final ScanCallback scanCallback = new ScanCallback() {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            result.getDevice().connectGatt(context.getApplicationContext(), false, mGattCallback);
        }
    };

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private byte[] encodeAES128(byte[] code){
        byte[] encodeBytes = null;
        try {
            SecretKeySpec keySpec = new SecretKeySpec(aesSecretKey.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(aesInitVector.getBytes(StandardCharsets.UTF_8));
            String algorithm = "AES/CBC/PKCS7Padding";
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            encodeBytes = cipher.doFinal(code);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return encodeBytes;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private byte[] decodeAES128(byte[] code){
        byte[] decodeBytes = null;
        try {
            SecretKeySpec keySpec = new SecretKeySpec(aesSecretKey.getBytes(StandardCharsets.UTF_8), "AES");
            IvParameterSpec ivSpec = new IvParameterSpec(aesInitVector.getBytes(StandardCharsets.UTF_8));
            String algorithm = "AES/CBC/PKCS7Padding";
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            decodeBytes = cipher.doFinal(code);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return decodeBytes;
    }
}
