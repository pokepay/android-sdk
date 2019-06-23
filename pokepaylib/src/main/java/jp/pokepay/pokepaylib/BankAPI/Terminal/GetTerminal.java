package jp.pokepay.pokepaylib.BankAPI.Terminal;

import jp.pokepay.pokepaylib.Responses.Terminal;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class GetTerminal extends BankRequest {

    public GetTerminal(){}

    protected final String path() {
        return "/terminal";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final Terminal send(String accessToken) {
        return super.send(Terminal.class, accessToken);
    }
}
