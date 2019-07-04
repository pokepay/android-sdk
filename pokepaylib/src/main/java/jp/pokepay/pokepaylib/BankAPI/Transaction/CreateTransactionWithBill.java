package jp.pokepay.pokepaylib.BankAPI.Transaction;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class CreateTransactionWithBill extends BankRequest {
    public String billId;
    public String accountId;
    public double amount;

    public CreateTransactionWithBill(String billId, String accountId, double amount) {
        this.billId    = billId;
        this.accountId = accountId;
        this.amount    = amount;
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
            put("bill_id", billId);
            put("account_id", accountId);
            put("amount", amount);
        }};
    }

    public final UserTransaction send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(UserTransaction.class, accessToken);
    }
}
