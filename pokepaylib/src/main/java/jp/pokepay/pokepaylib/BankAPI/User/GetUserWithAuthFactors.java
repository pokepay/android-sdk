package jp.pokepay.pokepaylib.BankAPI.User;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.PaginatedAccounts;
import jp.pokepay.pokepaylib.Responses.UserWithAuthFactors;

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
