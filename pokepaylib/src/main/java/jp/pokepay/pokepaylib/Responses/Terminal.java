package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Terminal {
    public String id;
    public String name;
    public String hardware_id;
    public String push_service;
    public String push_token;
    public User user;
    public Account account;

    public Terminal(){
        user = new User();
        account = new Account();
    }

    public void print(){
        System.out.println("Terminal(");
        System.out.println("id: \"" + id + "\",");
        System.out.println("name: \"" + name + "\",");
        System.out.println("hardwareId: \"" + hardware_id + "\",");
        System.out.println("pushService: \"" + push_service + "\",");
        System.out.println("pushToken: \"" + push_token + "\",");
        System.out.print("user: ");
        user.print();
        System.out.print("account: ");
        account.print();
        System.out.println("),");
    }
}
