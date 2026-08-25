package jp.pokepay.pokepaylib.BankAPI.User;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.UserWithAuthFactors;

/**
 * @deprecated Use {@link jp.pokepay.pokepaylib.BankAPI.autogen.requests.GetUserWithAuthFactors} instead.
 *     This hand-written request is being replaced by the auto-generated API.
 */
@Deprecated
public class GetUserWithAuthFactors extends BankRequest {
    @NonNull
    public String userId;

    public GetUserWithAuthFactors(@NonNull String userId) {
        this.userId = userId;
    }

    protected final String path() {
        return "/users/" + userId + "/auth-factors";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final UserWithAuthFactors send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(UserWithAuthFactors.class, accessToken);
    }
}
