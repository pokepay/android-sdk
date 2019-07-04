package jp.pokepay.pokepaylib.BankAPI.Cashtray;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.NoContent;

public class DeleteCashtray extends BankRequest {
    public String id;

    public DeleteCashtray(String id) {
        this.id = id;
    }

    protected final String path() {
        return "/cashtrays/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.DELETE;
    }

    public final NoContent send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(NoContent.class, accessToken);
    }
}
