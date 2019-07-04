package jp.pokepay.pokepaylib.MessagingAPI;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.MessageUnreadCount;

public class GetUnreadCount extends BankRequest {

    public GetUnreadCount() {}

    protected final String path() {
        return "/messages/unread-count";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final MessageUnreadCount send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(MessageUnreadCount.class, accessToken);
    }
}


