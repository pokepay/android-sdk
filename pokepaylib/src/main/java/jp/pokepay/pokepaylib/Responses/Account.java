package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Account {
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

    public void print(){
        System.out.println("Account(");
        System.out.println("id: \"" + id + "\",");
        System.out.println("name: \"" + name + "\",");
        System.out.println("balance: \"" + balance + "\",");
        System.out.println("moneyBalance: \"" + money_balance + "\",");
        System.out.println("pointBalance: \"" + point_balance + "\",");
        System.out.println("isSuspended: \"" + is_suspended + "\",");
        System.out.print("privateMoney: ");
        private_money.print();
        System.out.println("nearest_expires_at: \"" + nearest_expires_at + "\",");
        System.out.println("),");
    }
}
