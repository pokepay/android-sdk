package jp.pokepay.pokepaylib.BankAPI.CpmToken;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.AccountCpmToken;

public class GetCpmToken extends BankRequest {
    public String cpmToken;

    public GetCpmToken(String cpmToken) {
        this.cpmToken = cpmToken;
    }

    protected final String path() {
        return "/cpm/" + cpmToken;
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final AccountCpmToken send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(AccountCpmToken.class, accessToken);
    }
}
