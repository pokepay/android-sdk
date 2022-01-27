package jp.pokepay.pokepaylib.BankAPI.Account;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.CouponDetail;

public class PatchAccountCouponDetail extends BankRequest {
    @NonNull
    public String accountId;
    @NonNull
    public String couponId;
    @NonNull
    public boolean isReceived = true;
    public String code;

    public PatchAccountCouponDetail(String accountId, String couponId, boolean isReceived, String code) {
        this.accountId = accountId;
        this.couponId = couponId;
        this.isReceived = isReceived;
        this.code = code;
    }

    @Override
    protected String path() {
        return "/accounts/" + accountId + "/coupons/" + couponId;
    }

    @Override
    protected Request.Method method() {
        return Request.Method.PATCH;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("is_received",isReceived);
            if(code != null && !code.isEmpty()) put("code", code);
        }};
    }

    public final CouponDetail send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(CouponDetail.class, accessToken);
    }
}
