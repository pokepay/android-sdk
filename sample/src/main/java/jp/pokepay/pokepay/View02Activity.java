package jp.pokepay.pokepay;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jp.pokepay.pokepaylib.Pokepay;
import jp.pokepay.pokepaylib.Responses.AccessToken;
import jp.pokepay.pokepaylib.Responses.Bill;
import jp.pokepay.pokepaylib.Responses.Cashtray;
import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.Responses.Terminal;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class View02Activity extends AppCompatActivity {
    private String clientId = "LlMbGiWr7ouItgi5-7mRSQ";
    private String clientSecret = "2CZtBW6hzTCYRZhLLsPxdoeG7ktpDGrJ4GsfxTjLOjXFHQeizsSBUNR0";
    private String accessToken1 = "7mL_asUSVHUZhW11nDJzlm-Xa7-01VjgVBPi8Hd43UAqYpMCEfEuzLPGWfKr0VU9";
    private String accessToken2 = "oNTvWHFqv512JJQhUVgAwCx7LphHVpHFAp_jDMQ62THIN9iOwNfUXA9nMkI66xoA";// 購入客を想定(残高あり)
    Pokepay pokepay;
    EditText editTextOauth;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view02);

        pokepay = new Pokepay();

        progressDialog = new ProgressDialog(View02Activity.this);
        progressDialog.setTitle("通信中");
        progressDialog.setMessage("Please wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        editTextOauth = (EditText) findViewById(R.id.text_oauth);
        Button button0 = (Button)findViewById(R.id.button_oauth);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        pokepay.NewOAuthClient(clientId, clientSecret);
                        AccessToken accessToken = pokepay.oAuthClient.getAccessToken(editTextOauth.getText().toString());
                        String token = "error";
                        if(accessToken != null){
                            token = "Success! accessToken : ";
                            token += accessToken.access_token;
                        }
                        Message msg = Message.obtain();
                        msg.obj = token;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button1 = (Button)findViewById(R.id.button_terminal);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        pokepay.NewClient(accessToken1);
                        Terminal terminal = pokepay.client.getTerminalInfo();
                        String id = "error";
                        if(terminal != null){
                            id = "Success! id : ";
                            id += terminal.id;
                        }
                        Message msg = Message.obtain();
                        msg.obj = id;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button2 = (Button)findViewById(R.id.button_bill);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        pokepay.NewClient(accessToken1);
                        Bill bill = (Bill)pokepay.client.createToken(-1, "AndroidTest bill");
                        pokepay.NewClient(accessToken2, true);
                        String str = "https://api-dev.pokepay.jp/bills/" + bill.id;
                        UserTransaction userTransaction = pokepay.client.scanToken(str);
                        String id = "error";
                        if(userTransaction != null){
                            id = "Success! transactionID : ";
                            id += userTransaction.id;
                        }
                        Message msg = Message.obtain();
                        msg.obj = id;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button3 = (Button)findViewById(R.id.button_cashtray);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        pokepay.NewClient(accessToken2, true);
                        Cashtray cashtray = (Cashtray)pokepay.client.createToken(1, "AndroidTest cashtray");
                        cashtray.print();
                        pokepay.NewClient(accessToken1);
                        String str = "https://api-dev.pokepay.jp/cashtrays/" + cashtray.id;
                        UserTransaction userTransaction = pokepay.client.scanToken(str);
                        String id = "error";
                        if(userTransaction != null){
                            id = "Success! transactionID : ";
                            id += userTransaction.id;
                        }
                        Message msg = Message.obtain();
                        msg.obj = id;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button4 = (Button)findViewById(R.id.button_check);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        pokepay.NewClient(accessToken2);
                        Check check = (Check)pokepay.client.createToken(1, "AndroidTest check");
                        check.print();
                        pokepay.NewClient(accessToken1);
                        String str = "https://api-dev.pokepay.jp/checks/" + check.id;
                        UserTransaction userTransaction = pokepay.client.scanToken(str);
                        String id = "error";
                        if(userTransaction != null){
                            id = "Success! transactionID : ";
                            id += userTransaction.id;
                        }
                        Message msg = Message.obtain();
                        msg.obj = id;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button5 = (Button)findViewById(R.id.button_info);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        pokepay.NewClient(accessToken2);
                        Check check = (Check)pokepay.client.createToken(1, "AndroidTest check");
                        check.print();
                        pokepay.NewClient(accessToken1);
                        String str = "https://api-dev.pokepay.jp/checks/" + check.id;
                        Check check1 = (Check) pokepay.client.getTokenInfo(str);
                        String id = "error";
                        if(check1 != null){
                            id = "Success! getTokenInfo : ";
                            id += check1.id;
                        }
                        Message msg = Message.obtain();
                        msg.obj = id;
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
                AlertDialog.Builder builder = new AlertDialog.Builder(View02Activity.this);
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
