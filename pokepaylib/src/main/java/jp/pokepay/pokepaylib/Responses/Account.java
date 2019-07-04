package jp.pokepay.pokepaylib.Responses;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

public class Account extends Response {
    public String       id;
    public String       name;
    public double       balance;
    public double       money_balance;
    public double       point_balance;
    public boolean      is_suspended;
    public PrivateMoney private_money;
    public Date         nearest_expires_at;
}
