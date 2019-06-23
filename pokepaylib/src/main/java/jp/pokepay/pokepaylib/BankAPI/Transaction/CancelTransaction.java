package jp.pokepay.pokepaylib.BankAPI.Transaction;

import jp.pokepay.pokepaylib.Responses.NoContent;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class CancelTransaction extends BankRequest {
    public String id;

    public CancelTransaction(String id) {
        this.id = id;
    }

    protected final String path() {
        return "/transactions/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.DELETE;
    }

    public final NoContent send(String accessToken) {
        return super.send(NoContent.class, accessToken);
    }
}
