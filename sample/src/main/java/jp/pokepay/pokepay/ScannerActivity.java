package jp.pokepay.pokepay;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import jp.pokepay.pokepaylib.Pokeregi.BLEController;

public class ScannerActivity extends AppCompatActivity {
    private BLEController bleController;
    private String accessToken = "S-WAIYRN6rVdb77rYGgMeRQgMLuQ2ZAM0Fo8HfocrrTWxH7tsehCkD6JJSjGhs-0";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        Intent intent = getIntent();
        String code = intent.getStringExtra("serviceUUID");
        bleController = new BLEController(code,this);

        progressDialog = new ProgressDialog(ScannerActivity.this);
        progressDialog.setTitle("結果待ち");
        progressDialog.setMessage("Please wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);


        Button button1 = (Button)findViewById(R.id.button_scanner);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        // String str = bleController.getResult();
                        //System.out.println("BLEController getResult: " + str);
                        Message msg = Message.obtain();
                        //msg.obj = str;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                progressDialog.show();
            }
            else {
                progressDialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(ScannerActivity.this);
                builder.setMessage(msg.obj.toString())
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.show();
            }
        }
    };
}
