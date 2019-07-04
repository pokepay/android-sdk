package jp.pokepay.pokepaylib.Responses;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

public class Check extends Response {
    public String id;
    public double amount;
    public double money_amount;
    public double point_amount;
    public String description;
    public User   user;
    public PrivateMoney private_money;
    public boolean is_onetime;
    public boolean is_disabled;
    public Date expires_at;
    public String token;
}
