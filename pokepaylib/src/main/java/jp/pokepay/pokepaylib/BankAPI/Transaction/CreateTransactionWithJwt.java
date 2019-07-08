package jp.pokepay.pokepaylib.BankAPI.Transaction;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.JwtResult;

public class CreateTransactionWithJwt extends BankRequest {
    @NonNull
    public String data;
    public String accountId;

    public CreateTransactionWithJwt(String data, String accountId) {
        this.data = data;
        this.accountId = accountId;
    }

    protected final String path() {
        return "/transactions";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("data", data);
            put("account_id", accountId);
        }};
    }

    public final JwtResult send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(JwtResult.class, accessToken);
    }
}
