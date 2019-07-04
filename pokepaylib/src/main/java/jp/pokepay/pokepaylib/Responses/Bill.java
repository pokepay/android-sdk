package jp.pokepay.pokepaylib.Responses;

import jp.pokepay.pokepaylib.Response;

public class Bill extends Response {
    public String id;
    public double amount;
    public String description;
    public User   user;
    public PrivateMoney private_money;
    public boolean is_onetime;
    public boolean is_disabled;
    public String token;
    public double min_amount;
    public double max_amount;
}
