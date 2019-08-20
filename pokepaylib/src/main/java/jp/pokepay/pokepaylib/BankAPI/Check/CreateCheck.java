package jp.pokepay.pokepaylib.BankAPI.Check;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Check;

public class CreateCheck extends BankRequest {
    public Double amount;
    public String description;
    public String accountId;

    public CreateCheck(Double amount, String accountId,  String description) {
        this.amount = amount;
        this.accountId = accountId;
        this.description = description;
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
        }};
    }

    public final Check send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Check.class, accessToken);
    }
}
