package jp.pokepay.pokepaylib.MessagingAPI;

import android.support.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Message;

public class GetMessage extends BankRequest {
    @NonNull
    public String id;

    public GetMessage(@NonNull String id) {
        this.id = id;
    }

    protected final String path() {
        return "/messages/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final Message send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Message.class, accessToken);
    }
}
