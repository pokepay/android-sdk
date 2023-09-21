package jp.pokepay.pokepaylib.BankAPI.User;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.BankPay;

public class GetBankPay extends BankRequest {
    @NonNull
    public String id;
    public String privateMoneyId;

    public GetBankPay(@NonNull String id, String privateMoneyId) {
        this.id = id;
        this.privateMoneyId = privateMoneyId;
    }

    protected final String path() {
        return "/users/" + id + "/banks";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            if (privateMoneyId != null) {
                put("private_money_id", privateMoneyId);
            }
        }};
    }

    public final BankPay[] send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(BankPay[].class, accessToken);
    }
}
