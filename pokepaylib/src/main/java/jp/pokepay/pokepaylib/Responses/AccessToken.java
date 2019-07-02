package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccessToken extends Response {
    public String access_token;
    public String refresh_token;
    public String token_type;
    public int    expires_in;

    public AccessToken() {}
}
