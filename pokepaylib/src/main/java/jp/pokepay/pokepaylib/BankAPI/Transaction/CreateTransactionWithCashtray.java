package jp.pokepay.pokepaylib.BankAPI.Transaction;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class CreateTransactionWithCashtray extends BankRequest {

    public enum STRATEGY {
        POINT_PREFERRED("point_preferred"),
        MONEY_ONLY("money_only");

        private final String strategy;

        STRATEGY(String strategy) {
            this.strategy = strategy;
        }

        public String getStrategy() {
            return strategy;
        }
    }

    @NonNull
    public String cashtrayId;
    public String accountId;
    public String couponId;
    public STRATEGY strategy;

    public CreateTransactionWithCashtray(String cashtrayId, String accountId, String couponId, STRATEGY strategy) {
        this.cashtrayId = cashtrayId;
        this.accountId = accountId;
        this.couponId = couponId;
        this.strategy = strategy;
    }

    public CreateTransactionWithCashtray(String cashtrayId, String accountId, String couponId) {
        this(cashtrayId, accountId, couponId, STRATEGY.POINT_PREFERRED);
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
            put("cashtray_id", cashtrayId);
            put("account_id", accountId);
            put("coupon_id", couponId);
            put("strategy", strategy != null ? strategy.getStrategy() : null);
        }};
    }

    public final UserTransaction send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(UserTransaction.class, accessToken);
    }
}
