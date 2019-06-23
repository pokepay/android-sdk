package jp.pokepay.pokepaylib.BankAPI.Transaction;

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

    protected final String body() {
        String str = "{\"data\":\"" + data;
        if(accountId != null) {
            str += "\", \"account_id\":\"" + accountId;
        }
        str += "\"}";
        return str;
    }

    public final JwtResult send(String accessToken) {
        return super.send(JwtResult.class, accessToken);
    }
}
