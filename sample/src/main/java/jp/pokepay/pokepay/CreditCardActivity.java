package jp.pokepay.pokepay;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.BankAPI.autogen.requests.CreateCreditCard;
import jp.pokepay.pokepaylib.BankAPI.autogen.requests.GetCreditCards;
import jp.pokepay.pokepaylib.BankAPI.autogen.requests.TopupWithCreditCardMdkToken;
import jp.pokepay.pokepaylib.BankAPI.autogen.requests.TopupWithCreditCardMembership;
import jp.pokepay.pokepaylib.BankAPI.autogen.responses.CreditCard;
import jp.pokepay.pokepaylib.BankAPI.autogen.responses.PaginatedCreditCards;
import jp.pokepay.pokepaylib.PartnerAPI.Veritrans.GetVeritransToken;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Responses.VeritransToken;

public class CreditCardActivity extends AppCompatActivity {
    final String userId = "a0bd7408-5087-4459-9e74-a21d7acc18d4";
    final String accountId = "7a226542-feb2-4614-9e87-907745ff5342";
    final String accessToken = "55nF6HJRyGCHgvEQZP7Pd5K33ggX-C5ZH_q3eqqe5Cg1dGeV_G2-CZ0rmChJ1OcN";
    final String tokenApiKey = "9ad14759-7082-457e-a874-864c78edc05a";

    final int json = 1;
    final int html = 2;

    private WebView webView;

    private final Handler handler = new Handler(msg -> {
        switch (msg.what) {
            case json:
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(CreditCardActivity.this);
                builder.setMessage(msg.obj.toString()).setPositiveButton("ok", (dialog, id) -> {
                });
                builder.show();
                break;
            case html:
                String htmlResponse = (String) msg.obj;
                webView.loadData(htmlResponse, "text/html", "UTF-8");
                break;
        }
        return true;
    });

    private String fetchVeritransMdkToken(String cardNumber, String cardExpireDate, String securityCode, String tokenApiKey) {
        try {
            VeritransToken response = new GetVeritransToken(cardNumber, cardExpireDate, securityCode, tokenApiKey).send();
            return response.token;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditcard);

        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.setWebViewClient(new WebViewClient());

        Button listCreditCardButton = findViewById(R.id.list_credit_cards);
        listCreditCardButton.setOnClickListener(view -> new Thread(() -> {
            final PaginatedCreditCards response;
            try {
                response = new GetCreditCards(userId, "coilinc").send(accessToken);
            } catch (ProcessingError | BankRequestError e) {
                throw new RuntimeException(e);
            }

            final Message msg = Message.obtain();
            msg.what = json;
            msg.obj = response.toString();

            handler.sendMessage(msg);
        }).start());

        Button createCreditCardButton = findViewById(R.id.create_credit_card);
        createCreditCardButton.setOnClickListener(view -> new Thread(() -> {
            final String cardNumber = "4111111111111111";
            final String cardExpireDate = "12/23";
            final String securityCode = "123";
            final String token = fetchVeritransMdkToken(cardNumber, cardExpireDate, securityCode, tokenApiKey);

            final CreditCard response;
            try {
                response = new CreateCreditCard(userId, token, "coilinc").send(accessToken);
            } catch (ProcessingError | BankRequestError e) {
                throw new RuntimeException(e);
            }

            final Message msg = Message.obtain();
            msg.what = json;
            msg.obj = response.toString();

            handler.sendMessage(msg);
        }).start());

        Button topupWithCreditCardMdkToken = findViewById(R.id.topup_with_credit_card_mdk_token);
        topupWithCreditCardMdkToken.setOnClickListener(view -> new Thread(() -> {
            final String cardNumber = "4111111111111111";
            final String cardExpireDate = "12/28";
            final String securityCode = "123";
            final String token = fetchVeritransMdkToken(cardNumber, cardExpireDate, securityCode, tokenApiKey);
            final int amount = 42;

            final String text;

            try {
                text = new TopupWithCreditCardMdkToken(userId, token, accountId, amount, "coilinc").send(accessToken);
            } catch (ProcessingError | BankRequestError e) {
                throw new RuntimeException(e);
            }

            final Message msg = Message.obtain();
            msg.obj = text;
            msg.what = html;
            handler.sendMessage(msg);

        }).start());

        Button topupWithCreditCardVeritransMembership = findViewById(R.id.topup_with_credit_card_veritrans_membership);
        topupWithCreditCardVeritransMembership.setOnClickListener(view -> new Thread(() -> {
            final String cardRegisteredAt = "2024-07-19T09:17:32.329588Z";
            final int amount = 42;

            final String text;

            try {
                text = new TopupWithCreditCardMembership(userId, cardRegisteredAt, accountId, amount, "coilinc").send(accessToken);
            } catch (ProcessingError | BankRequestError e) {
                throw new RuntimeException(e);
            }

            final Message msg = Message.obtain();
            msg.obj = text;
            msg.what = html;
            handler.sendMessage(msg);
        }).start());
    }
}
