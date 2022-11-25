package jp.pokepay.pokepaylib.BankAPI.Check;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Check;

public class CreateCheck extends BankRequest {
    public double amount;
    public String description;
    public String accountId;
    public Date expiresAt;

    @Deprecated
    public CreateCheck(Double amount, String accountId, String description, Date expiresAt) {
        if (amount != null) {
            this.amount = amount;
        }
        this.accountId = accountId;
        this.description = description;
        this.expiresAt = expiresAt;
    }

    public CreateCheck(double amount, String accountId, String description, Date expiresAt) {
        this.amount = amount;
        this.accountId = accountId;
        this.description = description;
        this.expiresAt = expiresAt;
    }

    protected final String path() {
        return "/checks";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("amount", amount);
            put("description", description);
            put("account_id", accountId);
            put("expires_at", expiresAt != null ? Request.getFormatter().format(expiresAt) : null);
        }};
    }

    public final Check send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Check.class, accessToken);
    }
}
