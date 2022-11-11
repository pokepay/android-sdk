package jp.pokepay.pokepaylib.BankAPI.Transaction;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class SendToAccount extends BankRequest {
    @NonNull
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

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("amount", amount);
            put("receiver_terminal_id", receiverTerminalId);
            put("sender_account_id", senderAccountId);
            put("description", description);
        }};
    }

    public final UserTransaction send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(UserTransaction.class, accessToken);
    }
}
