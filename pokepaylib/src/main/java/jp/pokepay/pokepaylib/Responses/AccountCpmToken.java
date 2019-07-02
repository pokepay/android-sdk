package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountCpmToken extends Response {
    public String cpm_token;
    public Account account;
    public UserTransaction transaction;
    public String scopes[];
    public Date expires_at;
    public String additional_info;

    public AccountCpmToken(){
        account = new Account();
        transaction = new UserTransaction();
        scopes = new String[]{};
        expires_at = new Date();
    }
}
