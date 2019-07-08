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
    @NonNull
    public String token;
}
