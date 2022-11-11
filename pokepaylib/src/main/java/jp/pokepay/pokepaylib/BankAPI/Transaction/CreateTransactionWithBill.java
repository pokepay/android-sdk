package jp.pokepay.pokepaylib.BankAPI.Transaction;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.Parameters.TransactionStrategy;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class CreateTransactionWithBill extends BankRequest {

    @NonNull
    public String billId;
    public String accountId;
    public Double amount;
    public String couponId;
    public TransactionStrategy strategy;

    public CreateTransactionWithBill(@NonNull String billId, String accountId, Double amount,String couponId,
                                     TransactionStrategy strategy) {
        this.billId    = billId;
        this.accountId = accountId;
        this.amount    = amount;
        this.couponId = couponId;
        this.strategy = strategy;
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
            put("coupon_id", couponId);
            put("strategy", strategy != null ? strategy.getStrategy() : null);
        }};
    }

    public final UserTransaction send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(UserTransaction.class, accessToken);
    }
}
