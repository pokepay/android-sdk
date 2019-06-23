package jp.pokepay.pokepaylib.BankAPI.Terminal;

import jp.pokepay.pokepaylib.Responses.Terminal;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class UpdateTerminal extends BankRequest {
    public String accountId;
    public String name;
    public String pushToken;

    public UpdateTerminal(String accountId, String name, String pushToken) {
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

    protected final String body() {
        String str = "{\"account_id\":\"" + accountId;
        str += "\", \"name\":\"" + name;
        str += "\", \"push_token\":\"" + pushToken;
        str += "\"}";
        return str;
    }

    public final Terminal send(String accessToken) {
        return super.send(Terminal.class, accessToken);
    }
}
