package jp.pokepay.pokepay;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.Env;
import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequestError;
import jp.pokepay.pokepaylib.Pokepay;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Responses.AccessToken;
import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.Responses.Terminal;
import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.TokenInfo;

public class View02Activity extends AppCompatActivity {
    private String clientId = "LlMbGiWr7ouItgi5-7mRSQ";
    private String clientSecret = "2CZtBW6hzTCYRZhLLsPxdoeG7ktpDGrJ4GsfxTjLOjXFHQeizsSBUNR0";
    private String accessToken1 = "7mL_asUSVHUZhW11nDJzlm-Xa7-01VjgVBPi8Hd43UAqYpMCEfEuzLPGWfKr0VU9";
    private String accessToken2 = "oNTvWHFqv512JJQhUVgAwCx7LphHVpHFAp_jDMQ62THIN9iOwNfUXA9nMkI66xoA"; // 購入客を想定(残高あり)
    EditText editTextOauth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view02);

        progressDialog = new ProgressDialog(View02Activity.this);
        progressDialog.setTitle("通信中");
        progressDialog.setMessage("Please wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        Pokepay.setEnv(Env.DEVELOPMENT);

        editTextOauth = (EditText) findViewById(R.id.text_oauth);
        Button button0 = (Button)findViewById(R.id.button_oauth);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        final Pokepay.OAuthClient client = new Pokepay.OAuthClient(clientId, clientSecret);
                        try {
                            final String code = "piwRz0Rljk/AB63y0aZo7uYZ2GyjGdDR";
                            final AccessToken accessToken = client.getAccessToken(code);
                            msg.obj = "Success! accessToken : " + accessToken.toString();
                        } catch (ProcessingError e) {
                            msg.obj = "ProcessingError: " + e.getMessage();
                        } catch (OAuthRequestError e) {
                            msg.obj = "OAuthRequestError: " + e.toString();
                        }
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
                        final Message msg = Message.obtain();
                        try {
                            final Pokepay.Client client = new Pokepay.Client(accessToken1, View02Activity.this);
                            final Terminal terminal = client.getTerminalInfo();
                            msg.obj = "Success! terminal: " + terminal.toString();
                        } catch (ProcessingError e) {
                            msg.obj = "ProcessingError: " + e.getMessage();
                        } catch (BankRequestError e) {
                            msg.obj = "BankRequestError: " + e.toString();
                        }
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
                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        try {
                            Pokepay.Client client = new Pokepay.Client(accessToken1, View02Activity.this);
                            final String token = client.createToken(1.0, "AndroidTest bill");
                            client = new Pokepay.Client(accessToken2, View02Activity.this,true);
                            final UserTransaction userTransaction = client.scanToken(token);
                            msg.obj = "Success transaction: " + userTransaction.toString();
                        } catch (ProcessingError e) {
                            msg.obj = "ProcessingError: " + e.getMessage();
                        } catch (BankRequestError e) {
                            msg.obj = "BankRequestError: " + e.toString();
                        }
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
                        final Message msg = Message.obtain();
                        try {
                            Pokepay.Client client = new Pokepay.Client(accessToken2, View02Activity.this, true);
                            final String token = client.createToken(1.0, "AndroidTest cashtray");
                            client = new Pokepay.Client(accessToken1, View02Activity.this);
                            final UserTransaction userTransaction = client.scanToken(token);
                            msg.obj = "Success transaction: " + userTransaction.toString();
                        } catch (ProcessingError e) {
                            msg.obj = "ProcessingError: " + e.getMessage();
                        } catch (BankRequestError e) {
                            msg.obj = "BankRequestError: " + e.toString();
                        }
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
                        final Message msg = Message.obtain();
                        try {
                            Pokepay.Client client = new Pokepay.Client(accessToken2,View02Activity.this);
                            final String token = client.createToken(1.0, "AndroidTest check");
                            client = new Pokepay.Client(accessToken1,View02Activity.this);
                            UserTransaction userTransaction = client.scanToken(token);
                            msg.obj = "Success transaction: " + userTransaction.toString();
                        } catch (ProcessingError e) {
                            msg.obj = "ProcessingError: " + e.getMessage();
                        } catch (BankRequestError e) {
                            msg.obj = "BankRequestError: " + e.toString();
                        }
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
                        final Message msg = Message.obtain();
                        try {
                            Pokepay.Client client = new Pokepay.Client(accessToken2,View02Activity.this);
                            final String token = client.createToken(1.0, "AndroidTest check");
                            client = new Pokepay.Client(accessToken1,View02Activity.this);
                            final TokenInfo info = client.getTokenInfo(token);
                            if (info.type == TokenInfo.Type.CHECK) {
                                Check check1 = (Check) info.info;
                                if (check1 != null) {
                                    msg.obj = "Success! getTokenInfo: " + check1.toString();
                                } else {
                                    msg.obj = "Error! data was null";
                                }
                            } else {
                                msg.obj = "Error! unmatched info.type";
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
        });
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                progressDialog.show();
            } else {
                progressDialog.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(View02Activity.this);
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
