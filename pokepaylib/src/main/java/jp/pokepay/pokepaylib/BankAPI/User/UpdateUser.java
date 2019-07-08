package jp.pokepay.pokepaylib.BankAPI.User;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.User;

public class UpdateUser extends BankRequest {
    @NonNull
    public String id;
    public String name;

    public UpdateUser(String id, String name) {
        this.id   = id;
        this.name = name;
    }

    protected final String path() {
        return "/users/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.PATCH;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("name", name);
        }};
    }

    public final User send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(User.class, accessToken);
    }
}
