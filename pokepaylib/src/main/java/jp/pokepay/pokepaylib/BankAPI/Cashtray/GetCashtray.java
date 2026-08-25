package jp.pokepay.pokepaylib.BankAPI.Cashtray;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Cashtray;

/**
 * @deprecated Use {@link jp.pokepay.pokepaylib.BankAPI.autogen.requests.GetCashtray} instead.
 *     This hand-written request is being replaced by the auto-generated API.
 */
@Deprecated
public class GetCashtray extends BankRequest {
    @NonNull
    public String id;

    public GetCashtray(@NonNull String id) {
        this.id = id;
    }

    protected final String path() {
        return "/cashtrays/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final Cashtray send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Cashtray.class, accessToken);
    }
}
