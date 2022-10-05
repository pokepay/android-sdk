package jp.pokepay.pokepay;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.Pokepay;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.TokenInfo;

public class QRCameraActivity extends AppCompatActivity {
    private String url;
    private ProgressDialog progressDialog;

    private String customerAccessToken = "S-WAIYRN6rVdb77rYGgMeRQgMLuQ2ZAM0Fo8HfocrrTWxH7tsehCkD6JJSjGhs-0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcamera);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        IntentIntegrator ii = new IntentIntegrator(QRCameraActivity.this);
        ii.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        ii.setPrompt("Scan a QRCode");
        ii.setOrientationLocked(true);
        ii.setCameraId(0);  // Use a specific camera of the device
        ii.setBeepEnabled(false);
        ii.setBarcodeImageEnabled(false);
        ii.initiateScan();

        progressDialog = new ProgressDialog(QRCameraActivity.this);
        progressDialog.setTitle("通信中");
        progressDialog.setMessage("Please wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "BLE not supported", Toast.LENGTH_SHORT).show();
            return;
        }
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            final String code = result.getContents();
            if(code == null) {
                Log.d("PokepayBLE", "cancelled");
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        Log.d("PokepayBLE", "found: " + code);
                        Pokepay.Client c = new Pokepay.Client(customerAccessToken, QRCameraActivity.this, false);
                        try {
                            TokenInfo token = c.getTokenInfo(code);
                            Log.d("PokepayBLE", "tokenInfo: " + token.type);
                            UserTransaction tr = c.scanToken(code);
                            if (tr != null) {
                                msg.obj = "Success transaction: " + tr.toString();
                            } else {
                                msg.obj = "done";
                            }
                        } catch (ProcessingError e) {
                            msg.obj = "ProcessingError: " + e.getMessage();
                        } catch (BankRequestError e) {
                            msg.obj = "BankRequestError: " + e.toString();
                        }
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                progressDialog.show();
            } else {
                progressDialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(QRCameraActivity.this);
                builder.setMessage(msg.obj.toString())
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.show();
            }
            return true;
        }
    });
}
