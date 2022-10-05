package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class PaginatedCoupons extends Response {
    public int     per_page;
    public int     count;
    public String  next;
    public String  prev;
    @NonNull
    public Coupon items[];
}
