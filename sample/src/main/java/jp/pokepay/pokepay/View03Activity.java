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

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;


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
                        final Message msg = Message.obtain();
                        try {
                            final String result = lowLevelAPITests.AccountTest();
                            msg.obj = "AccountTest: \n" + result;
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

        Button button1 = (Button)findViewById(R.id.buttonBill);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        try {
                            final String result = lowLevelAPITests.BillTest();
                            msg.obj = "BillTest: \n" + result;
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

        Button button2 = (Button)findViewById(R.id.buttonCashtray);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        try {
                            final String result = lowLevelAPITests.CashtrayTest();
                            msg.obj = "CashtrayTest: \n" + result;
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

        Button button3 = (Button)findViewById(R.id.buttonCheck);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        try {
                            final String result = lowLevelAPITests.CheckTest();
                            msg.obj = "CheckTest: \n" + result;
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

        Button button4 = (Button)findViewById(R.id.buttonPrivateMoney);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        try {
                            final String result = lowLevelAPITests.PrivateMoneyTest();
                            msg.obj = "PrivateMoneyTest: \n" + result;
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

        editTextTerminal = (EditText) findViewById(R.id.textTerminal);
        Button button5 = (Button)findViewById(R.id.buttonTerminal);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        try {
                            final String result = lowLevelAPITests.TerminalTest(editTextTerminal.getText().toString());
                            msg.obj = "TerminalTest: \n" + result;
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

        Button button6 = (Button)findViewById(R.id.buttonTransaction);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        try {
                            final String result = lowLevelAPITests.TransactionTest();
                            msg.obj = "TransactionTest: \n" + result;
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

        editTextUser = (EditText) findViewById(R.id.textUser);
        Button button7 = (Button)findViewById(R.id.buttonUser);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        try {
                            final String result = lowLevelAPITests.UserTest(editTextUser.getText().toString());
                            msg.obj = "UserTest: \n" + result;
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

        Button button8 = (Button)findViewById(R.id.buttonMessage);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        final MessagingAPITests messagingAPITests = new MessagingAPITests();
                        final String res = messagingAPITests.AllTests();
                        msg.obj = "result: " + res;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button9 = (Button)findViewById(R.id.buttonCpm);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        try {
                            final String result = lowLevelAPITests.CpmTest();
                            msg.obj = "CpmTest: \n" + result;
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

        Button button10 = (Button)findViewById(R.id.buttonParseAsPokeregiToken);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        try {
                            final String result = lowLevelAPITests.ParseAsPokeregiTokenTest();
                            msg.obj = "ParseAsPokeregiToken: \n" + result;
                        } catch (ProcessingError e) {
                            msg.obj = "ProcessingError: " + e.toString();
                        } catch (BankRequestError e) {
                            msg.obj = "BankRequestError: " + e.toString();
                        }
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        Button button11 = (Button)findViewById(R.id.buttonConfirmNewParametersShouldBeIgnored);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.sendEmptyMessage(1);
                        final Message msg = Message.obtain();
                        try {
                            final String result = lowLevelAPITests.ConfirmNewParametersShouldBeIgnoredTest();
                            msg.obj = "ParseAsPokeregiToken: \n" + result;
                        } catch (ProcessingError e) {
                            msg.obj = "ProcessingError: " + e.toString();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(View03Activity.this);
                builder.setMessage(msg.obj.toString())
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        });
                builder.show();
                progressDialog.dismiss();
            }
            return true;
        }
    });
}
