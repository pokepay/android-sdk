package jp.pokepay.pokepaylib.BankAPI.User;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class BankPayTopUp extends BankRequest {
    @NonNull
    public String id;
    @NonNull
    public String accountId;
    @NonNull
    public String bankId;
    @NonNull
    public String amount;
    public String requestId;

    public BankPayTopUp(@NonNull String id, @NonNull String accountId, @NonNull String bankId, @NonNull String amount, String requestId) {
        this.id = id;
        this.accountId = accountId;
        this.bankId = bankId;
        this.amount = amount;
        this.requestId = requestId;
    }

    protected final String path() {
        return "/users/" + id + "/banks/topup";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("account_id", accountId);
            put("bank_id", bankId);
            put("amount", amount);
            if (requestId != null) {
                put("request_id", requestId);
            }
        }};
    }

    public final UserTransaction send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(UserTransaction.class, accessToken);
    }
}
