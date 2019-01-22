package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Organization {
    public String code;
    public String name;

    public void print() {
        System.out.println("Organization(");
        System.out.println("code: \"" + code + "\",");
        System.out.println("name: \"" + name + "\",");
        System.out.println("),");
    }
}
