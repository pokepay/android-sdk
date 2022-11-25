package jp.pokepay.pokepaylib.BankAPI.Cashtray;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Cashtray;

public class UpdateCashtray extends BankRequest {
    @NonNull
    public String id;
    public double amount;
    public String description;
    public int expiresIn = 0;

    @Deprecated
    public UpdateCashtray(@NonNull String id, Double amount, String description, Integer expiresIn) {
        this.id = id;
        if (amount != null) {
            this.amount = amount;
        }
        this.description = description;
        if (expiresIn != null) {
            this.expiresIn = expiresIn;
        }
    }

    public UpdateCashtray(@NonNull String id, String description, double amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }

    public UpdateCashtray(@NonNull String id, double amount, String description, int expiresIn) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.expiresIn = expiresIn;
    }

    protected final String path() {
        return "/cashtrays/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.PATCH;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("amount", amount);
            put("description", description);
            put("expires_in", expiresIn);
        }};
    }

    public final Cashtray send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Cashtray.class, accessToken);
    }
}
