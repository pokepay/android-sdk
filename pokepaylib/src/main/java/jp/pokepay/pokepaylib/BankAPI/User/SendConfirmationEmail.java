package jp.pokepay.pokepaylib.BankAPI.User;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.NoContent;

public class SendConfirmationEmail extends BankRequest {
    @NonNull
    public String id;
    @NonNull
    public String email;

    public SendConfirmationEmail(@NonNull String id, @NonNull String email) {
        this.id   = id;
        this.email = email;
    }

    protected final String path() {
        return "/users/" + id + "/emails/" + email;
    }

    protected final Request.Method method() {
        return Request.Method.PUT;
    }

    public final NoContent send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(NoContent.class, accessToken);
    }
}
