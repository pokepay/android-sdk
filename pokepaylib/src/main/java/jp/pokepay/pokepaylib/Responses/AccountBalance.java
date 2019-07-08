package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

public class AccountBalance extends Response {
    @NonNull
    public Date expires_at;
    @NonNull
    public double money_amount;
    @NonNull
    public double point_amount;
}
