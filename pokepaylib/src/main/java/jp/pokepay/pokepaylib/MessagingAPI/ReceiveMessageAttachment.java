package jp.pokepay.pokepaylib.MessagingAPI;

import android.support.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.MessageAttachment;

public class ReceiveMessageAttachment extends BankRequest {
    @NonNull
    public String id;

    public ReceiveMessageAttachment(@NonNull String id) {
        this.id = id;
    }

    protected final String path() {
        return "/messages/" + id + "/attachment/receive";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    public final MessageAttachment send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(MessageAttachment.class, accessToken);
    }
}
