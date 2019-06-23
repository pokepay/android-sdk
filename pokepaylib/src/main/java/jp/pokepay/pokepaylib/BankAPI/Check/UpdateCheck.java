package jp.pokepay.pokepaylib.BankAPI.Check;

import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

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

    protected final String body() {
        boolean flag = false;
        String str = "{";
        if(amount > 0) {
            str += "\"amount\":\"" + (int) amount;
            flag = true;
        }
        if(description != null) {
            str += "\", \"description\":\"" + description;
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
