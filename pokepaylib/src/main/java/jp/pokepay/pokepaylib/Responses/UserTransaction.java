package jp.pokepay.pokepaylib.Responses;

import jp.pokepay.pokepaylib.Response;

public class UserTransaction extends Response {
    public String  id;
    public User    user;
    public double  balance;
    public double  customer_balance;
    public double  amount;
    public double  money_amount;
    public double  point_amount;
    public Account account;
    public String  description;
    public String  done_at;
    public String  type;
    public boolean is_modified;
}
