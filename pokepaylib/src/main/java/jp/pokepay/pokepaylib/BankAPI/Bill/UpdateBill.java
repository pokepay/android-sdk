package jp.pokepay.pokepaylib.BankAPI.Bill;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Bill;

public class UpdateBill extends BankRequest {
    @NonNull
    public String id;
    public Double amount;
    public String description;

    public UpdateBill(@NonNull String id, Double amount, String description) {
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
            put("amount", amount);
            put("description", description);
        }};
    }

    public final Bill send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Bill.class, accessToken);
    }
}
