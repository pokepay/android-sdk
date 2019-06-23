package jp.pokepay.pokepaylib.BankAPI.Transaction;

import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

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

    protected final String body() {
        String str = "{\"bill_id\":\"" + billId;
        if(accountId != null) {
            str += "\", \"account_id\":\"" + accountId;
        }
        if(amount >= 0) {
            str += "\", \"amount\":\"" + amount;
        }
        str += "\"}";
        return str;
    }

    public final UserTransaction send(String accessToken) {
        return super.send(UserTransaction.class, accessToken);
    }
}
