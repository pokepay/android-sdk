package jp.pokepay.pokepaylib.BankAPI.Terminal;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Terminal;

public class UpdateTerminal extends BankRequest {
    @NonNull
    public String accountId;
    @NonNull
    public String name;
    public String pushToken;

    public UpdateTerminal(@NonNull String accountId, @NonNull String name, String pushToken) {
        this.accountId = accountId;
        this.name      = name;
        this.pushToken = pushToken;
    }

    protected final String path() {
        return "/terminal";
    }

    protected final Request.Method method() {
        return Request.Method.PATCH;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("account_id", accountId);
            put("name", name);
            put("push_token", pushToken);
        }};
    }

    public final Terminal send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Terminal.class, accessToken);
    }
}
