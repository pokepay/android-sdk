package jp.pokepay.pokepaylib.BankAPI.Terminal;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
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

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("key", key);
        }};
    }

    public final ServerKey send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(ServerKey.class, accessToken);
    }
}
