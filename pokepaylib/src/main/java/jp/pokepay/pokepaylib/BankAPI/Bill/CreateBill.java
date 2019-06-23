package jp.pokepay.pokepaylib.BankAPI.Bill;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("amount", amount >= 0 ? amount : null);
            put("description", description);
            put("account_id", accountId);
        }};
    }

    public final Bill send(String accessToken) {
        return super.send(Bill.class, accessToken);
    }
}
