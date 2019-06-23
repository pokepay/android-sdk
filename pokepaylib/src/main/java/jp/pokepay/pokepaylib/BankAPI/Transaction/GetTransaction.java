package jp.pokepay.pokepaylib.BankAPI.Transaction;

import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class GetTransaction extends BankRequest {
    public String id;

    public GetTransaction(String id) {
        this.id = id;
    }

    protected final String path() {
        return "/transactions/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final UserTransaction send(String accessToken) {
        return super.send(UserTransaction.class, accessToken);
    }
}
