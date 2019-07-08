package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

public class Account extends Response {
    @NonNull
    public String       id;
    @NonNull
    public String       name;
    public double       balance;
    public double       money_balance;
    public double       point_balance;
    public boolean      is_suspended;
    @NonNull
    public PrivateMoney private_money;
    public Date         nearest_expires_at;
}
