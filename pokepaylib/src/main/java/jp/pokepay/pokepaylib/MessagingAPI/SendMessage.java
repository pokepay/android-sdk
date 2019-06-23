package jp.pokepay.pokepaylib.MessagingAPI;

import jp.pokepay.pokepaylib.Responses.Message;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class SendMessage extends BankRequest {
    public String toUserId;
    public double amount;
    public String subject;
    public String body;
    public String fromAccountId;
    public String requestId;

    public SendMessage(String toUserId, double amount, String subject, String body, String fromAccountId, String requestId) {
        this.toUserId = toUserId;
        this.amount = amount;
        this.subject = subject;
        this.body = body;
        this.fromAccountId = fromAccountId;
        this.requestId = requestId;
    }

    protected final String path() {
        return "/messages";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    protected final String body() {
        String str = "{\"to_user_id\":\"" + toUserId;
        if(amount > -1){
            str += "\", \"amount\":\"" + amount;
        }
        str += "\", \"subject\":\"" + subject;
        str += "\", \"body\":\"" + body;
        if(fromAccountId != null) {
            str += "\", \"from_account_id\":\"" + fromAccountId;
        }
        if(requestId != null) {
            str += "\", \"_request_id\":\"" + requestId;
        }
        str += "\"}";
        return str;
    }

    public final Message send(String accessToken) {
        return super.send(Message.class, accessToken);
    }
}
