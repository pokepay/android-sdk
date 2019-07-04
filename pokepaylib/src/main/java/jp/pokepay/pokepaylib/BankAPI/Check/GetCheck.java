package jp.pokepay.pokepaylib.BankAPI.Check;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Check;

public class GetCheck extends BankRequest {
    public String id;

    public GetCheck(String id) {
        this.id = id;
    }

    protected final String path() {
        return "/checks/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final Check send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Check.class, accessToken);
    }
}
