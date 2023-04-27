package jp.pokepay.pokepaylib.BankAPI.User;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.PrivateMoney;

public class GetUserPrivateMoneys extends BankRequest {
    @NonNull
    public String userId;

    public GetUserPrivateMoneys(@NonNull String userId) {
        this.userId = userId;
    }

    protected final String path() {
        return "/users/" + userId + "/private-moneys";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final PrivateMoney[] send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(PrivateMoney[].class, accessToken);
    }
}
