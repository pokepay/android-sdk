package jp.pokepay.pokepaylib.BankAPI.Cashtray;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.NoContent;

/**
 * @deprecated Use {@link jp.pokepay.pokepaylib.BankAPI.autogen.requests.DeleteCashtray} instead.
 *     This hand-written request is being replaced by the auto-generated API.
 */
@Deprecated
public class DeleteCashtray extends BankRequest {
    @NonNull
    public String id;

    public DeleteCashtray(@NonNull String id) {
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
