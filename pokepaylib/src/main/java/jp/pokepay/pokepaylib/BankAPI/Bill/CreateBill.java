package jp.pokepay.pokepaylib.BankAPI.Bill;

import jp.pokepay.pokepaylib.Responses.Bill;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class CreateBill extends BankRequest {
    public double amount;
    public String description;
    public String accountId;

    public CreateBill(double amount, String description, String accountId) {
        this.amount = amount;
        this.description = description;
        this.accountId = accountId;
    }

    protected final String path() {
        return "/bills";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    protected final String body() {
        String str = "{\"amount\":\"" + (int)amount;
        if(description != null) {
            str += "\", \"description\":\"" + description;
        }
        if(accountId != null) {
            str += "\", \"account_id\":\"" + accountId;
        }
        str += "\"}";
        return str;
    }

    public final Bill send(String accessToken) {
        return super.send(Bill.class, accessToken);
    }
}
