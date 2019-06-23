package jp.pokepay.pokepaylib.BankAPI.User;

import jp.pokepay.pokepaylib.Responses.NoContent;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class RegisterUserEmail extends BankRequest {
    public String token;

    public RegisterUserEmail(String token) {
        this.token = token;
    }

    protected final String path() {
        return "/emails";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    protected final String body() {
        String str = "{\"token\":\"" + token;
        str += "\"}";
        return str;
    }

    public final NoContent send(String accessToken) {
        return super.send(NoContent.class, accessToken);
    }
}
