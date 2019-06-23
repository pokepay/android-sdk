package jp.pokepay.pokepaylib.BankAPI.Account;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.Responses.Account;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class CreateAccount extends BankRequest {
    public String name;
    public String privateMoneyId;

    public CreateAccount(String name, String privateMoneyId) {
        this.name = name;
        this.privateMoneyId = privateMoneyId;
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
        }};
    }

    public final Account send(String accessToken) {
        return super.send(Account.class, accessToken);
    }
}
