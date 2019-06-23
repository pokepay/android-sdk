package jp.pokepay.pokepaylib.BankAPI.Cashtray;

import jp.pokepay.pokepaylib.Responses.Cashtray;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class UpdateCashtray extends BankRequest {
    public String id;
    public double amount;
    public String description;
    public int    expiresIn;

    public UpdateCashtray(String id, double amount, String description, int expiresIn) {
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

    protected final String body() {
        boolean flag = false;
        String str = "{";
        if(amount >= 0) {
            str += "\"amount\":\"" + (int) amount;
            flag = true;
        }
        if(description != null) {
            str += "\", \"description\":\"" + description;
            flag = true;
        }
        if(expiresIn >= 0){
            str += "\", \"expires_in\":\"" + expiresIn;
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

    public final Cashtray send(String accessToken) {
        return super.send(Cashtray.class, accessToken);
    }
}
