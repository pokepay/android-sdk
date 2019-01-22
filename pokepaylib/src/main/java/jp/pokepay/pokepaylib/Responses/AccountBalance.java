package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountBalance {
    public Date expires_at;
    public double money_amount;
    public double point_amount;

    public AccountBalance(){
        expires_at = new Date();
    }

    public void print(){
        System.out.println("AccountBalance(");
        System.out.println("expires_at: \"" + expires_at + "\",");
        System.out.println("money_amount: \"" + money_amount + "\",");
        System.out.println("point_amount: \"" + point_amount + "\",");
        System.out.println("),");
    }

}
