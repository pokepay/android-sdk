package jp.pokepay.pokepaylib.BankAPI.Transaction;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.Responses.JwtResult;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class CreateTransactionWithJwt extends BankRequest {
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

    public final JwtResult send(String accessToken) {
        return super.send(JwtResult.class, accessToken);
    }
}
