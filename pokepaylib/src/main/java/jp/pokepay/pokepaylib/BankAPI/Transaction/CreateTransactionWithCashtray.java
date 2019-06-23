package jp.pokepay.pokepaylib.BankAPI.Transaction;

import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class CreateTransactionWithCashtray extends BankRequest {
    public String cashtrayId;
    public String accountId;

    public CreateTransactionWithCashtray(String cashtrayId, String accountId) {
        this.cashtrayId = cashtrayId;
        this.accountId = accountId;
    }
    protected final String path() {
        return "/transactions";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    protected final String body() {
        String str = "{\"cashtray_id\":\"" + cashtrayId;
        if(accountId != null) {
            str += "\", \"account_id\":\"" + accountId;
        }
        str += "\"}";
        return str;
    }

    public final UserTransaction send(String accessToken) {
        return super.send(UserTransaction.class, accessToken);
    }
}
