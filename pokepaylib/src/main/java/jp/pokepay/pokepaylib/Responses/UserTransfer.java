package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class UserTransfer extends Response {
    @NonNull
    public String  id;
    @NonNull
    public String  type;
    public double  amount;
    public double  balance;
    @NonNull
    public String  description;
    public User    user;
    public Account account;
    @NonNull
    public String  done_at;
    public double  money_amount;
    public double  point_amount;
    public String  transaction_id;
}