package jp.pokepay.pokepaylib.MessagingAPI;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Message;

public class SendMessage extends BankRequest {
    @NonNull
    public String toUserId;
    public Double amount;
    @NonNull
    public String subject;
    @NonNull
    public String body;
    public String fromAccountId;
    @NonNull
    public String requestId;

    public SendMessage(String toUserId, Double amount, String subject, String body, String fromAccountId) {
        this.toUserId = toUserId;
        this.amount = amount;
        this.subject = subject;
        this.body = body;
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
            put("amount", amount);
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
