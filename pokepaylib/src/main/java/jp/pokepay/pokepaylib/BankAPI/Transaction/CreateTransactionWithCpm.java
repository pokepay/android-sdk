package jp.pokepay.pokepaylib.BankAPI.Transaction;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.Parameters.Product;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class CreateTransactionWithCpm extends BankRequest {
    @NonNull
    public String cpmToken;
    public String accountId;
    public double amount;
    public Product[] products;

    @Deprecated
    public CreateTransactionWithCpm(@NonNull String cpmToken, String accountId, Double amount, Product[] products) {
        this.cpmToken  = cpmToken;
        this.accountId = accountId;
        if (amount != null){
            this.amount = amount;
        }
        this.products  = products;
    }

    public CreateTransactionWithCpm(@NonNull String cpmToken, String accountId, double amount, Product[] products){
        this.cpmToken = cpmToken;
        this.accountId = accountId;
        this.amount = amount;
        this.products = products;
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
            put("cpm_token", cpmToken);
            put("account_id", accountId);
            put("amount", amount);
            if (products != null) {
                Map<String, Object>[] productsMap = new Map[products.length];
                for (int i = 0; i < products.length; i++) {
                    productsMap[i] = products[i].toMap();
                }
                put("products", productsMap);
            }
        }};
    }

    public final UserTransaction send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(UserTransaction.class, accessToken);
    }
}
