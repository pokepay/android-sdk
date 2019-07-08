package jp.pokepay.pokepaylib.BankAPI.Transaction;

import android.support.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class GetTransaction extends BankRequest {
    @NonNull
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

    public final UserTransaction send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(UserTransaction.class, accessToken);
    }
}
