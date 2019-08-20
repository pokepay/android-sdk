package jp.pokepay.pokepaylib.BankAPI.Cashtray;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.Parameters.Product;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Cashtray;

public class CreateCashtray extends BankRequest {
    public double amount;
    public String description;
    public Integer expiresIn;
    public Product[] products;

    public CreateCashtray(double amount, String description, Integer expiresIn, Product[] products) {
        this.amount = amount;
        this.description = description;
        this.expiresIn = expiresIn;
        this.products = products;
    }

    protected final String path() {
        return "/cashtrays";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("amount", amount);
            put("description", description);
            put("expires_in", expiresIn);
            if (products != null) {
                Map<String, Object>[] productsMap = new Map[products.length];
                for (int i = 0; i < products.length; i++) {
                    productsMap[i] = products[i].toMap();
                }
                put("products", productsMap);
            }
        }};
    }

    public final Cashtray send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Cashtray.class, accessToken);
    }
}
