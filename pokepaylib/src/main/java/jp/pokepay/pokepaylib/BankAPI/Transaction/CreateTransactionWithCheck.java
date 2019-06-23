package jp.pokepay.pokepaylib.BankAPI.Transaction;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class CreateTransactionWithCheck extends BankRequest {
    public String checkId;
    public String accountId;

    public CreateTransactionWithCheck(String checkId, String accountId){
        this.checkId   = checkId;
        this.accountId = accountId;
    }

    protected final String path() {
        return "/transactions";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("check_id", checkId);
            put("account_id", accountId);
        }};
    }

    public final UserTransaction send(String accessToken) {
        return super.send(UserTransaction.class, accessToken);
    }
}
