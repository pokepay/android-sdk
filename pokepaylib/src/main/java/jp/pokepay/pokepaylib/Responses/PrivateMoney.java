package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class PrivateMoney extends Response {
    @NonNull
    public String       id;
    @NonNull
    public String       name;
    @NonNull
    public String       type;
    @NonNull
    public String       unit;
    @NonNull
    public String       description;
    @NonNull
    public String       oneline_message;
    public String       account_image;
    @NonNull
    public Images       images;
    @NonNull
    public Organization organization;
    public double       max_balance;
    public double       transfer_limit;
    @NonNull
    public String       expiration_type;
    public boolean      is_exclusive;
    public String       terms_url;
    public String       privacy_policy_url;
    public String       payment_act_url;
    public String       commercial_act_url;
    @NonNull
    public boolean      can_use_credit_card;
    @NonNull
    public boolean      can_use_c2c_transfer;
    public String       custom_domain_name;
}

