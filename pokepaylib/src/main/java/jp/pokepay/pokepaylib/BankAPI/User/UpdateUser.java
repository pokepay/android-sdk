package jp.pokepay.pokepaylib.BankAPI.User;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.Responses.User;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class UpdateUser extends BankRequest {
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

    public final User send(String accessToken) {
        return super.send(User.class, accessToken);
    }
}
