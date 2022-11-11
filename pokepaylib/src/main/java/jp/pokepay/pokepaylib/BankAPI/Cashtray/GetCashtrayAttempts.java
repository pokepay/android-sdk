package jp.pokepay.pokepaylib.BankAPI.Cashtray;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.CashtrayAttempts;

public class GetCashtrayAttempts extends BankRequest {
    @NonNull
    public String id;

    public GetCashtrayAttempts(@NonNull String id) {
        this.id = id;
    }

    protected final String path() {
        return "/cashtrays/" + id + "/attempts";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final CashtrayAttempts send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(CashtrayAttempts.class, accessToken);
    }
}
