package jp.pokepay.pokepaylib.BankAPI.Check;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Response;
import jp.pokepay.pokepaylib.Responses.Check;

public class CreateCheck extends BankRequest {
    public Double amount;
    public Double moneyAmount;
    public Double pointAmount;
    public String accountId;
    public String description;
    public boolean isOnetime;
    public Integer usageLimit;
    public Date expiresAt;
    public Date pointExpiresAt;
    public Integer pointExpiresInDays;

    public CreateCheck(
        Double amount, Double moneyAmount, Double pointAmount,
        String accountId, String description, boolean isOnetime,
        Integer usageLimit, Date expiresAt, Date pointExpiresAt, Integer pointExpiresInDays)
    {
        this.amount = amount;
        this.moneyAmount = moneyAmount;
        this.pointAmount = pointAmount;
        this.accountId = accountId;
        this.description = description;
        this.isOnetime = isOnetime;
        this.usageLimit = usageLimit;
        this.expiresAt = expiresAt;
        this.pointExpiresAt = pointExpiresAt;
        this.pointExpiresInDays = pointExpiresInDays;
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
            put("money_amount", moneyAmount);
            put("point_amount", pointAmount);
            put("account_id", accountId);
            put("description", description);
            put("is_onetime", isOnetime);
            put("usage_limit", usageLimit);
            if (expiresAt != null) {
                put("expires_at", Response.formatter.format(expiresAt));
            }
            if (pointExpiresAt != null) {
                put("point_expires_at", Response.formatter.format(pointExpiresAt));
            }
            put("point_expires_in_days", pointExpiresInDays);
        }};
    }

    public final Check send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Check.class, accessToken);
    }
}
