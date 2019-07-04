package jp.pokepay.pokepaylib.Responses;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

public class Cashtray extends Response {
    public String id;
    public double amount;
    public String description;
    public User user;
    public PrivateMoney private_money;
    public Date expires_at;
    public Date canceled_at;
    public String token;
}
