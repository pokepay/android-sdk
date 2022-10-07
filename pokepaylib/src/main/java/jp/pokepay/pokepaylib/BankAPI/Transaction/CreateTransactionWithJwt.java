package jp.pokepay.pokepaylib.BankAPI.Transaction;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.JwtResult;

public class CreateTransactionWithJwt extends BankRequest {

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
    public String data;
    public String accountId;
    public String couponId;
    public STRATEGY strategy;

    public CreateTransactionWithJwt(String data, String accountId, String couponId, STRATEGY strategy) {
        this.data = data;
        this.accountId = accountId;
        this.couponId = couponId;
    }

    public CreateTransactionWithJwt(String data, String accountId, String couponId) {
        this(data, accountId, couponId, STRATEGY.POINT_PREFERRED);
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
            put("data", data);
            put("account_id", accountId);
            put("coupon_id", couponId);
            put("strategy", strategy != null ? strategy.getStrategy() : null);
        }};
    }

    public final JwtResult send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(JwtResult.class, accessToken);
    }
}
