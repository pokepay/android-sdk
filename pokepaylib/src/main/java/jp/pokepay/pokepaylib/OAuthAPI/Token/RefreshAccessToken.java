package jp.pokepay.pokepaylib.OAuthAPI.Token;

import jp.pokepay.pokepaylib.Responses.AccessToken;
import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequest;
import jp.pokepay.pokepaylib.Request;

public class RefreshAccessToken extends OAuthRequest {
    public String grantType = "refresh_token";
    public String refreshToken;
    public String clientId;
    public String clientSecret;

    public RefreshAccessToken(String refreshToken, String clientId, String clientSecret) {
        this.refreshToken = refreshToken;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    protected final String path() {
        return "/oauth/token";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    protected final String body() {
        String str = "{";
        str += "\"refresh_token\":\"" + refreshToken;
        str += "\", \"grant_type\":\"" + grantType;
        str += "\", \"client_id\":\"" + clientId;
        str += "\", \"client_secret\":\"" + clientSecret;
        str += "\"}";
        return str;
    }

    public final AccessToken send() {
        return super.send(AccessToken.class);
    }
}
