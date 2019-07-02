package jp.pokepay.pokepaylib.BankAPI.Check;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class CreateCheck extends BankRequest {
    public double amount;
    public String description;
    public String accountId;

    public CreateCheck(double amount, String description, String accountId) {
        this.amount = amount;
        this.description = description;
        this.accountId = accountId;
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
            put("amount", amount > 0 ? amount : null);
            put("description", description);
            put("account_id", accountId);
        }};
    }

    public final Check send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Check.class, accessToken);
    }
}
