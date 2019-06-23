package jp.pokepay.pokepaylib.BankAPI.Check;

import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

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

    public final Check send(String accessToken) {
        return super.send(Check.class, accessToken);
    }
}
