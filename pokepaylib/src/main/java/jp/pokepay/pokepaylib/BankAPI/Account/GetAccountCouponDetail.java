package jp.pokepay.pokepaylib.BankAPI.Account;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.CouponDetail;

public class GetAccountCouponDetail extends BankRequest {
    @NonNull
    public String accountId;
    @NonNull
    public String couponId;

    public GetAccountCouponDetail(@NonNull String accountId, @NonNull String couponId){
        this.accountId = accountId;
        this.couponId = couponId;
    }
    @Override
    protected String path() {
        return "/accounts/"+accountId+"/coupons/"+couponId;
    }

    @Override
    protected Request.Method method() {
        return Request.Method.GET;
    }

    public final CouponDetail send(String accessToken) throws ProcessingError, BankRequestError{
        return super.send(CouponDetail.class,accessToken);
    }
}
