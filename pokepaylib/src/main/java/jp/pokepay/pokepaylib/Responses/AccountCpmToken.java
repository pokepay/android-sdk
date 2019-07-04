package jp.pokepay.pokepaylib.Responses;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

public class AccountCpmToken extends Response {
    public String cpm_token;
    public Account account;
    public UserTransaction transaction;
    public String scopes[];
    public Date expires_at;
    public String additional_info;
}
