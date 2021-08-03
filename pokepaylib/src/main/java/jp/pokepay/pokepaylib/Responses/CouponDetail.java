package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

public class CouponDetail extends Coupon {

    public String received_at;
    @NonNull
    public int usage_count;
    @NonNull
    public User[] available_shops;
}