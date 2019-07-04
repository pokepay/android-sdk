package jp.pokepay.pokepaylib.BankAPI.Terminal;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Terminal;

public class GetTerminal extends BankRequest {

    public GetTerminal(){}

    protected final String path() {
        return "/terminal";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final Terminal send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Terminal.class, accessToken);
    }
}
