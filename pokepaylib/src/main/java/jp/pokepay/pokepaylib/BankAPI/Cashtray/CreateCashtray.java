package jp.pokepay.pokepaylib.BankAPI.Cashtray;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Cashtray;

public class CreateCashtray extends BankRequest {
    public double amount;
    public String description;
    public Integer expiresIn;

    public CreateCashtray(double amount, String description, Integer expiresIn) {
        this.amount = amount;
        this.description = description;
        this.expiresIn = expiresIn;
    }

    protected final String path() {
        return "/cashtrays";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
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
