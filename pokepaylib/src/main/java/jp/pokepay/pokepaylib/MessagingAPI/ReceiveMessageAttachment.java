package jp.pokepay.pokepaylib.MessagingAPI;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Message;
import jp.pokepay.pokepaylib.Responses.MessageAttachment;

public class ReceiveMessageAttachment extends BankRequest {
    public Message message;

    public ReceiveMessageAttachment(Message message) {
        this.message = message;
    }

    protected final String path() {
        return "/messages/" + message.id + "/attachment/receive";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    public final MessageAttachment send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(MessageAttachment.class, accessToken);
    }
}
