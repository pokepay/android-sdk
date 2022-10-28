package jp.pokepay.pokepaylib.MessagingAPI;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Message;
import jp.pokepay.pokepaylib.Responses.Terminal;
import jp.pokepay.pokepaylib.Responses.User;

public class SendMessage extends BankRequest {
    @NonNull
    public String toUserId;
    public double amount;
    public String subject;
    public String body;
    @NonNull
    public User sender;
    public String fromAccountId;
    public String requestId;

    /**
     * @param amount: only can be add when sender is merchant.
     * **/
    public SendMessage(@NonNull String toUserId, Double amount, String subject, String body,
                       @NonNull User sender, String fromAccountId) {
        this.toUserId = toUserId;
        this.amount = amount;
        this.subject = subject;
        this.body = body;
        this.sender = sender;
        this.fromAccountId = fromAccountId;
        this.requestId = UUID.randomUUID().toString();
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
            if(sender.is_merchant){
                put("amount", amount);
            }
            put("subject", subject);
            put("body", body);
            put("from_account_id", fromAccountId);
            put("_request_id", requestId);
        }};
    }

    public final Message send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Message.class, accessToken);
    }
}
