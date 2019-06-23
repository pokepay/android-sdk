package jp.pokepay.pokepaylib.MessagingAPI;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("to_user_id", toUserId);
            put("amount", amount > 0 ? amount : null);
            put("subject", subject);
            put("body", body);
            put("from_account_id", fromAccountId);
            put("request_id", requestId);
        }};
    }

    public final Message send(String accessToken) {
        return super.send(Message.class, accessToken);
    }
}
