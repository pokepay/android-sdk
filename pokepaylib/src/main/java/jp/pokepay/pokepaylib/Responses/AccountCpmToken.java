package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Map;

import jp.pokepay.pokepaylib.Response;

public class AccountCpmToken extends Response {
    @NonNull
    public String cpm_token;
    @NonNull
    public Account account;
    public UserTransaction transaction;
    @NonNull
    public String scopes[];
    @NonNull
    public Date expires_at;
    public Map<String, String> metadata;
}
