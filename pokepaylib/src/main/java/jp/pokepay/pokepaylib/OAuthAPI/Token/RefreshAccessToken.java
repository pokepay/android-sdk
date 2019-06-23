package jp.pokepay.pokepaylib.OAuthAPI.Token;

import java.util.HashMap;
import java.util.Map;

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
    
    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("refresh_token", refreshToken);
            put("grant_type", grantType);
            put("client_id", clientId);
            put("client_secret", clientSecret);
        }};
    }

    public final AccessToken send() {
        return super.send(AccessToken.class);
    }
}
