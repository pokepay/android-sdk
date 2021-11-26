package jp.pokepay.pokepaylib.BankAPI.Account;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Account;

public class CreateAccount extends BankRequest {
    public String name;
    public String privateMoneyId;
    public String externalId;

    public CreateAccount(String name, String privateMoneyId, String externalId) {
        this.name = name;
        this.privateMoneyId = privateMoneyId;
        this.externalId = externalId;
    }

    protected final String path() {
        return "/accounts";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("name", name);
            put("private_money_id", privateMoneyId);
            if(externalId != null && !externalId.isEmpty()) put("external_id", externalId);
        }};
    }

    public final Account send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Account.class, accessToken);
    }
}
