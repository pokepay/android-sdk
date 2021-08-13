package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

public class Coupon extends Response {
    @NonNull
    public String id;
    @NonNull
    public String name;
    @NonNull
    public String description;
    public int discount_amount;
    public int discount_percentage;
    @NonNull
    public Date starts_at;
    @NonNull
    public Date ends_at;
    @NonNull
    public Date display_starts_at;
    @NonNull
    public Date display_ends_at;
    public int usage_limit;
    public int min_amount;
    @NonNull
    public boolean is_shop_specified;
    @NonNull
    public boolean is_disabled;
    @NonNull
    public boolean is_hidden;
    public String coupon_image;
}
