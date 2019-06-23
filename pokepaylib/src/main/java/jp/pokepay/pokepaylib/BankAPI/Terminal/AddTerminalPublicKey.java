package jp.pokepay.pokepaylib.BankAPI.Terminal;

import jp.pokepay.pokepaylib.Responses.ServerKey;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class AddTerminalPublicKey extends BankRequest {
    public String key;

    public AddTerminalPublicKey(String key) {
        this.key = key;
    }

    protected final String path() {
        return "/terminal/keys";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    protected final String body() {
        return "{\"key\":\"" + key + "\"}";
    }

    public final ServerKey send(String accessToken) {
        return super.send(ServerKey.class, accessToken);
    }
}
