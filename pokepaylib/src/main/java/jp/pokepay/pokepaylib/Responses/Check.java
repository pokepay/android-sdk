package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

public class Check extends Response {
    @NonNull
    public String id;
    @NonNull
    public double amount;
    @NonNull
    public double money_amount;
    @NonNull
    public double point_amount;
    @NonNull
    public String description;
    @NonNull
    public User   user;
    @NonNull
    public PrivateMoney private_money;
    @NonNull
    public boolean is_onetime;
    @NonNull
    public boolean is_disabled;
    @NonNull
    public Date expires_at;
    public Date point_expires_at;
    public Integer point_expires_in_days;
    @NonNull
    public String token;
}
