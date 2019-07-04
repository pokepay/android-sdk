package jp.pokepay.pokepaylib.Responses;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

public class AccountBalance extends Response {
    public Date expires_at;
    public double money_amount;
    public double point_amount;
}
