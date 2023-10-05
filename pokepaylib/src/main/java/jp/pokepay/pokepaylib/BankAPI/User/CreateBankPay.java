package jp.pokepay.pokepaylib.BankAPI.User;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.BankPayRedirectUrl;

public class CreateBankPay extends BankRequest {
    @NonNull
    public String id;
    @NonNull
    public String callbackUrl;
    @NonNull
    public String privateMoneyId;
    public String kana;

    public CreateBankPay(@NonNull String id, @NonNull String callbackUrl, @NonNull String privateMoneyId, String kana) {
        this.id = id;
        this.callbackUrl = callbackUrl;
        this.privateMoneyId = privateMoneyId;
        this.kana = kana;
    }

    protected final String path() {
        return "/users/" + id + "/banks";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("callback_url", callbackUrl);
            put("private_money_id", privateMoneyId);

            if (kana != null) {
                put("kana", kana);
            }
        }};
    }

    public final BankPayRedirectUrl send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(BankPayRedirectUrl.class, accessToken);
    }
}
