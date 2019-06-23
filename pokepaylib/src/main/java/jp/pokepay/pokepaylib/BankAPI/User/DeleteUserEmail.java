package jp.pokepay.pokepaylib.BankAPI.User;

import jp.pokepay.pokepaylib.Responses.NoContent;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class DeleteUserEmail extends BankRequest {
    public String id;
    public String email;

    public DeleteUserEmail(String id, String email){
        this.id = id;
        this.email = email;
    }

    protected final String path() {
        return "/users/" + id + "/emails/" + email;
    }

    protected final Request.Method method() {
        return Request.Method.DELETE;
    }

    public final NoContent send(String accessToken) {
        return super.send(NoContent.class, accessToken);
    }
}
