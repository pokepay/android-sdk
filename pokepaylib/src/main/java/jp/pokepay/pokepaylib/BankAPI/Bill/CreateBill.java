package jp.pokepay.pokepaylib.BankAPI.Bill;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.Parameters.Product;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Bill;

public class CreateBill extends BankRequest {
    public Double amount;
    public String description;
    public String accountId;
    public Product[] products;

    public CreateBill(Double amount, String accountId, String description, Product[] products) {
        this.amount = amount;
        this.accountId = accountId;
        this.description = description;
        this.products = products;
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
            if (products != null) {
                Map<String, Object>[] productsMap = new Map[products.length];
                for (int i = 0; i < products.length; i++) {
                    productsMap[i] = products[i].toMap();
                }
                put("products", productsMap);
            }
        }};
    }

    public final Bill send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Bill.class, accessToken);
    }
}
