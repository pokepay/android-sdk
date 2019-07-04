package jp.pokepay.pokepaylib.Responses;

import jp.pokepay.pokepaylib.Response;

public class PrivateMoney extends Response {
    public String       id;
    public String       name;
    public String       type;
    public String       unit;
    public String       description;
    public String       oneline_message;
    public String       account_image;
    public Image        images;
    public Organization organization;
    public double       max_balance;
    public double       transfer_limit;
    public String       expiration_type;
    public boolean      is_exclusive;
    public String       terms_url;
    public String       privacy_policy_url;
    public String       payment_act_url;
    public String       commercial_act_url;
}

