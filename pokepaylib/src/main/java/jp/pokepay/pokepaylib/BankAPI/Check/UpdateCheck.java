package jp.pokepay.pokepaylib.BankAPI.Check;

import android.support.annotation.NonNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Response;
import jp.pokepay.pokepaylib.Responses.Check;

public class UpdateCheck extends BankRequest {
    @NonNull
    public String id;
    public Double amount;
    public String description;
    public Date expiresAt;
    public Date pointExpiresAt;
    public Integer pointExpiresInDays;

    public UpdateCheck(String id, Double amount, String description, Date expiresAt, Date pointExpiresAt, Integer pointExpiresInDays) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.expiresAt = expiresAt;
        this.pointExpiresAt = pointExpiresAt;
        this.pointExpiresInDays = pointExpiresInDays;
    }

    protected final String path() {
        return "/checks/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.PATCH;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("amount", amount);
            put("description", description);
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
