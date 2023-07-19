package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class Bill extends Response {
    @NonNull
    public String id;
    public Double amount;
    @NonNull
    public String description;
    @NonNull
    public User   user;
    @NonNull
    public PrivateMoney private_money;
    public boolean is_onetime;
    public boolean is_disabled;
    @NonNull
    public String token;
    public Double min_amount;
    public Double max_amount;
    public PrivateMoney[] additional_private_moneys;
}
