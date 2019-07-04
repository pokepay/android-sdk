package jp.pokepay.pokepaylib.BankAPI.Check;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Check;

public class UpdateCheck extends BankRequest {
    public String id;
    public double amount;
    public String description;

    public UpdateCheck(String id, double amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
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
            put("amount", amount >= 0 ? amount : null);
            put("description", description);
        }};
    }

    public final Check send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Check.class, accessToken);
    }
}
