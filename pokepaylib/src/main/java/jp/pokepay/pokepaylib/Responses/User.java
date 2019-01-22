package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
    public String id;
    public String name;
    public String is_merchant;

    public User(){

    }

    public void print(){
        System.out.println("User(");
        System.out.println("id: \"" + id + "\",");
        System.out.println("name: \"" + name + "\",");
        System.out.println("isMerchant: \"" + is_merchant + "\",");
        System.out.println("),");
    }
}
