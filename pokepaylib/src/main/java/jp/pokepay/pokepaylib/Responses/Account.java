package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Account extends Response {
    public String       id;
    public String       name;
    public double       balance;
    public double       money_balance;
    public double       point_balance;
    public boolean      is_suspended;
    public PrivateMoney private_money;
    public String       nearest_expires_at;

    public Account(){
        private_money = new PrivateMoney();
    }
}
