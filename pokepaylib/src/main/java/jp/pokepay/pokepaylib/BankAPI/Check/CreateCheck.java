package jp.pokepay.pokepaylib.BankAPI.Check;

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

    protected final String body() {
        boolean flag = false;
        String str = "{";
        if(amount != -1) {
            str += "\"amount\":\"" + amount;
            flag = true;
        }
        if(description != null) {
            str += "\", \"description\":\"" + description;
            flag = true;
        }
        if(accountId != null) {
            str += "\", \"account_id\":\"" + accountId;
            flag = true;
        }
        if(flag) {
            str += "\"}";
        }
        else{
            str += "}";
        }
        return str;
    }

    public final Check send(String accessToken) {
        return super.send(Check.class, accessToken);
    }
}
