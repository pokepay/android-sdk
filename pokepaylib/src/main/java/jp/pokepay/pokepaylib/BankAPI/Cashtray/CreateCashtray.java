package jp.pokepay.pokepaylib.BankAPI.Cashtray;

import jp.pokepay.pokepaylib.Responses.Cashtray;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class CreateCashtray extends BankRequest {

    public double amount;
    public String description;
    public int expiresIn;

    public CreateCashtray(double amount, String description, int expiresIn) {
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

    protected final String body() {
        String str = "{\"amount\":\"" + (int)amount;
        if (description != null) {
            str += "\", \"description\":\"" + description;
        }
        if (expiresIn != -1) {
            str += "\", \"expires_in\":\"" + expiresIn;
        }
        str += "\"}";
        return str;
    }

    public final Cashtray send(String accessToken) {
        return super.send(Cashtray.class, accessToken);
    }
}
