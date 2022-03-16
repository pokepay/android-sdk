package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class UserTransaction extends Response {
    @NonNull
    public String  id;
    @NonNull
    public String  type;
    @NonNull
    public boolean is_modified;
    @NonNull
    public User    user;
    public double  balance;
    public Double  customer_balance;
    public double  amount;
    public double  money_amount;
    public double  point_amount;
    @NonNull
    public Account account;
    @NonNull
    public String  description;
    @NonNull
    public String  done_at;
    public UserTransfer transfers[];
}
