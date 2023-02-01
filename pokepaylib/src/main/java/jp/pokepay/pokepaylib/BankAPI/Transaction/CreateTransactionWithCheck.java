package jp.pokepay.pokepaylib.BankAPI.Transaction;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class CreateTransactionWithCheck extends BankRequest {
    @NonNull
    public String checkId;
    public String accountId;
    public UUID requestId;

    public CreateTransactionWithCheck(@NonNull String checkId, String accountId) {
        this.checkId   = checkId;
        this.accountId = accountId;
    }

    public CreateTransactionWithCheck(@NonNull String checkId, String accountId, UUID requestId) {
        this.checkId   = checkId;
        this.accountId = accountId;
        this.requestId = requestId;
    }

    protected final String path() {
        return "/transactions";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("check_id", checkId);
            put("account_id", accountId);
            put("request_id", requestId != null ? requestId.toString(): null);
        }};
    }

    public final UserTransaction send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(UserTransaction.class, accessToken);
    }
}
