package jp.pokepay.pokepaylib.BankAPI.Transaction;

import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class SendToUser extends BankRequest {
    public String userId;
    public double amount;
    public String receiverTerminalId;
    public String senderAccountId;
    public String description;

    public SendToUser(String userId, double amount, String receiverTerminalId, String senderAccountId, String description) {
        this.userId = userId;
        this.amount = amount;
        this.receiverTerminalId = receiverTerminalId;
        this.senderAccountId = senderAccountId;
        this.description = description;
    }

    protected final String path() {
        return "/users/" + userId + "/transactions";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    protected final String body() {
        String str = "{\"amount\":\"" + amount;
        if (receiverTerminalId != null) {
            str += "\", \"receiver_terminal_id\":\"" + receiverTerminalId;
        }
        if (senderAccountId != null) {
            str += "\", \"sender_account_id\":\"" + senderAccountId;
        }
        if (description != null) {
            str += "\", \"description\":\"" + description;
        }
        str += "\"}";
        return str;
    }

    public final UserTransaction send(String accessToken) {
        return super.send(UserTransaction.class, accessToken);
    }
}
