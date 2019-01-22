package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Bill {
    public String id;
    public double amount;
    public String description;
    public User   user;
    public PrivateMoney private_money;
    public boolean is_onetime;
    public boolean is_disabled;
    public String token;

    public Bill(){
        user = new User();
        private_money = new PrivateMoney();
    }

    public void print(){
        System.out.println("Bill(");
        System.out.println("id: \"" + id + "\",");
        System.out.println("amount: \"" + amount + "\",");
        System.out.println("description: \"" + description + "\",");
        System.out.print("User: ");
        user.print();
        System.out.println("),");
    }
}
