package jp.pokepay.pokepaylib.BankAPI.Transaction;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.JwtResult;

public class CreateTransactionWithJwt extends BankRequest {
    @NonNull
    public String data;
    public String accountId;
    public String couponId;

    public CreateTransactionWithJwt(String data, String accountId, String couponId) {
        this.data = data;
        this.accountId = accountId;
        this.couponId = couponId;
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
        }};
    }

    public final JwtResult send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(JwtResult.class, accessToken);
    }
}
