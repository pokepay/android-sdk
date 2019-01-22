package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PrivateMoney {
    public String       id;
    public String       name;
    public String       type;
    public String       unit;
    public String       description;
    public String       oneline_message;
    public String       account_image;
    public Image images;
    public Organization organization;
    public double       max_balance;
    public double       transfer_limit;
    public String       expiration_type;
    public boolean      is_exclusive;
    public String       terms_url;
    public String       privacy_policy_url;
    public String       payment_act_url;
    public String       commercial_act_url;

    public PrivateMoney(){
        images       = new Image();
        organization = new Organization();
    }

    public void print(){
        System.out.println("PrivateMoney(");
        System.out.println("id: \"" + id + "\",");
        System.out.println("name: \"" + name + "\",");
        System.out.println("type: \"" + type + "\",");
        System.out.println("unit: \"" + unit + "\",");
        System.out.println("description: \"" + description + "\",");
        System.out.println("onelineMessage: \"" + oneline_message + "\",");
        System.out.println("accountImage: \"" + account_image + "\",");
        System.out.print("Images: ");
        images.print();
        System.out.print("Organization: ");
        organization.print();
        System.out.println("maxBalance: \"" + max_balance + "\",");
        System.out.println("transferLimit: \"" + transfer_limit + "\",");
        System.out.println("expirationType: \"" + expiration_type + "\",");
        System.out.println("isExclusive: \"" + is_exclusive + "\",");
        System.out.println("),");
    }
}

