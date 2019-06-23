package jp.pokepay.pokepaylib.BankAPI.Transaction;

import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class SendToAccount extends BankRequest {
    public String accountId;
    public double amount;
    public String receiverTerminalId;
    public String senderAccountId;
    public String description;

    public SendToAccount(String accountId, double amount, String receiverTerminalId, String senderAccountId, String description) {
        this.accountId = accountId;
        this.amount = amount;
        this.receiverTerminalId = receiverTerminalId;
        this.senderAccountId = senderAccountId;
        this.description = description;
    }

    protected final String path() {
        return "/accounts/" + accountId + "/transactions";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    protected final String body() {
        int a = (int)amount;
        String str = "{\"amount\":\"" + a;
        if(receiverTerminalId != null) {
            str += "\", \"receiver_terminal_id\":\"" + receiverTerminalId;
        }
        if(senderAccountId != null){
            str += "\", \"sender_account_id\":\"" + senderAccountId;
        }
        if(description != null){
            str += "\", \"description\":\"" + description;
        }
        str += "\"}";
        return str;
    }

    public final UserTransaction send(String accessToken) {
        return super.send(UserTransaction.class, accessToken);
    }
}
