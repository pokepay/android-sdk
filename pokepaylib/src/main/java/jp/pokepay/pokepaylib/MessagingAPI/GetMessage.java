package jp.pokepay.pokepaylib.MessagingAPI;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Responses.Message;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class GetMessage extends BankRequest {
    public String id;

    public GetMessage(String id) {
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
