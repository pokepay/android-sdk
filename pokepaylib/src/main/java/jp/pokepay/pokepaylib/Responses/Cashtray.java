package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Cashtray {
    public String id;
    public double amount;
    public String description;
    public User user;
    public PrivateMoney private_money;
    public String expires_at;
    public String token;

    public Cashtray(){
        user = new User();
        private_money = new PrivateMoney();
    }

    public void print(){
        System.out.println("Cashtray(");
        System.out.println("id: \"" + id + "\",");
        System.out.println("amount: \"" + amount + "\",");
        System.out.println("description: \"" + description + "\",");
        System.out.print("User: ");
        user.print();
        System.out.println("expiresAt: \"" + expires_at + "\",");
        System.out.println("),");
    }

}
