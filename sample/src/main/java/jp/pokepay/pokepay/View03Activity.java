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


public class View03Activity extends AppCompatActivity {
    LowLevelAPITests lowLevelAPITests;
    EditText editTextTerminal, editTextUser;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view03);

        lowLevelAPITests = new LowLevelAPITests();

        progressDialog = new ProgressDialog(View03Activity.this);
        progressDialog.setTitle("通信中");
        progressDialog.setMessage("Please wait...");
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        Button button0 = (Button)findViewById(R.id.buttonAccount);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        boolean result = lowLevelAPITests.AccountTest();
                        Message msg = Message.obtain();
                        msg.obj = "result : " + String.valueOf(result);
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button1 = (Button)findViewById(R.id.buttonBill);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        boolean result = lowLevelAPITests.BillTest();
                        Message msg = Message.obtain();
                        msg.obj = "result : " + String.valueOf(result);
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button2 = (Button)findViewById(R.id.buttonCashtray);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        boolean result = lowLevelAPITests.CashtrayTest();
                        Message msg = Message.obtain();
                        msg.obj = "result : " + String.valueOf(result);
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button3 = (Button)findViewById(R.id.buttonCheck);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        boolean result = lowLevelAPITests.CheckTest();
                        Message msg = Message.obtain();
                        msg.obj = "result : " + String.valueOf(result);
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button4 = (Button)findViewById(R.id.buttonPrivateMoney);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        boolean result = lowLevelAPITests.PrivateMoneyTest();
                        Message msg = Message.obtain();
                        msg.obj = "result : " + String.valueOf(result);
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        editTextTerminal = (EditText) findViewById(R.id.textTerminal);
        Button button5 = (Button)findViewById(R.id.buttonTerminal);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        boolean result = lowLevelAPITests.TerminalTest(editTextTerminal.getText().toString());
                        Message msg = Message.obtain();
                        msg.obj = "result : " + String.valueOf(result);
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button6 = (Button)findViewById(R.id.buttonTransaction);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        boolean result = lowLevelAPITests.TransactionTest();
                        Message msg = Message.obtain();
                        msg.obj = "result : " + String.valueOf(result);
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        editTextUser = (EditText) findViewById(R.id.textUser);
        Button button7 = (Button)findViewById(R.id.buttonUser);
        button7.setEnabled(false);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        boolean result = lowLevelAPITests.UserTest(editTextUser.getText().toString());
                        Message msg = Message.obtain();
                        msg.obj = "result : " + String.valueOf(result);
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button8 = (Button)findViewById(R.id.buttonMessage);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        MessagingAPITests messagingAPITests = new MessagingAPITests();
                        boolean result = messagingAPITests.AllTests();
                        Message msg = Message.obtain();
                        msg.obj = "result : " + String.valueOf(result);
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });
    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                progressDialog.show();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(View03Activity.this);
                builder.setMessage(msg.obj.toString())
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.show();
                progressDialog.dismiss();
            }
        }
    };
}
