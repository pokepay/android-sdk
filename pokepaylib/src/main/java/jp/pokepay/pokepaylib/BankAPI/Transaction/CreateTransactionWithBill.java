package jp.pokepay.pokepaylib.BankAPI.Transaction;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
    public double amount;
    public String couponId;
    public TransactionStrategy strategy;
    public UUID requestId;

    @Deprecated
    public CreateTransactionWithBill(@NonNull String billId, String accountId, Double amount, String couponId, TransactionStrategy strategy) {
        this.billId = billId;
        this.accountId = accountId;
        if (amount != null) {
            this.amount = amount;
        }
        this.couponId = couponId;
        this.strategy = strategy;
    }

    public CreateTransactionWithBill(@NonNull String billId, String accountId, String couponId) {
        this.billId = billId;
        this.accountId = accountId;
        this.couponId = couponId;
    }

    public CreateTransactionWithBill(@NonNull String billId, String accountId, String couponId, double amount) {
        this.billId = billId;
        this.accountId = accountId;
        this.couponId = couponId;
        this.amount = amount;
    }

    public CreateTransactionWithBill(@NonNull String billId, String accountId, String couponId, TransactionStrategy strategy) {
        this.billId = billId;
        this.accountId = accountId;
        this.couponId = couponId;
        this.strategy = strategy;
    }

    public CreateTransactionWithBill(@NonNull String billId, String accountId, double amount, String couponId, TransactionStrategy strategy) {
        this.billId = billId;
        this.accountId = accountId;
        this.amount = amount;
        this.couponId = couponId;
        this.strategy = strategy;
    }

    public CreateTransactionWithBill(@NonNull String billId, String accountId, double amount, String couponId, TransactionStrategy strategy, UUID requestId) {
        this.billId = billId;
        this.accountId = accountId;
        this.amount = amount;
        this.couponId = couponId;
        this.strategy = strategy;
        this.requestId = requestId;
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
            put("request_id", requestId != null ? requestId.toString(): null);
        }};
    }

    public final UserTransaction send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(UserTransaction.class, accessToken);
    }
}
