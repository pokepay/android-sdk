package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class AccessToken extends Response {
    @NonNull
    public String access_token;
    @NonNull
    public String refresh_token;
    @NonNull
    public String token_type;
    public int    expires_in;
}
