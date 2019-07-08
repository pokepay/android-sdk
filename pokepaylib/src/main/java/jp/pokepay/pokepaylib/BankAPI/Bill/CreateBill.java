package jp.pokepay.pokepaylib.BankAPI.Bill;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Bill;

public class CreateBill extends BankRequest {
    public Double amount;
    public String description;
    public String accountId;

    public CreateBill(Double amount, String description, String accountId) {
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
            put("amount", amount);
            put("description", description);
            put("account_id", accountId);
        }};
    }

    public final Bill send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Bill.class, accessToken);
    }
}
