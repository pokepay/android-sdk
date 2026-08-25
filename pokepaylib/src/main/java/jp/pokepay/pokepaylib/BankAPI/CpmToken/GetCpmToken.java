package jp.pokepay.pokepaylib.BankAPI.CpmToken;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.AccountCpmToken;

/**
 * @deprecated Use {@link jp.pokepay.pokepaylib.BankAPI.autogen.requests.GetCpmToken} instead.
 *     This hand-written request is being replaced by the auto-generated API.
 */
@Deprecated
public class GetCpmToken extends BankRequest {
    @NonNull
    public String cpmToken;

    public GetCpmToken(@NonNull String cpmToken) {
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
