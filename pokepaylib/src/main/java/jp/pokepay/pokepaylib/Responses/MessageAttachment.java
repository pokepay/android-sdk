package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MessageAttachment {
    public double money_amount;
    public double point_amount;
    public boolean is_received;
    public Date expires_at;

    public MessageAttachment(){
        expires_at = new Date();
    }

    public void print(){
        System.out.println("PrivateMoney(");
        System.out.println("moneyAmount: \"" + money_amount + "\",");
        System.out.println("pointAmount: \"" + point_amount + "\",");
        System.out.println("isReceived: \"" + is_received + "\",");
        System.out.println("expiresAt: \"" + expires_at + "\",");
        System.out.println("),");
    }
}
