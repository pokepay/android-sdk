package jp.pokepay.pokepaylib.Responses;

import jp.pokepay.pokepaylib.Response;

public class AccessToken extends Response {
    public String access_token;
    public String refresh_token;
    public String token_type;
    public int    expires_in;
}
