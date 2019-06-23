package jp.pokepay.pokepaylib.BankAPI.Bill;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.Responses.Bill;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class UpdateBill extends BankRequest {
    public String id;
    public double amount;
    public String description;

    public UpdateBill(String id, double amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
    }

    protected final String path() {
        return "/bills/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.PATCH;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("amount", amount >= 0 ? amount : null);
            put("description", description);
        }};
    }

    public final Bill send(String accessToken) {
        return super.send(Bill.class, accessToken);
    }
}
