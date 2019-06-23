package jp.pokepay.pokepaylib.OAuthAPI.Token;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.Responses.AccessToken;
import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequest;
import jp.pokepay.pokepaylib.Request;

public class ExchangeAuthCode extends OAuthRequest {
    public String code;
    public String grantType = "authorization_code";
    public String clientId;
    public String clientSecret;

    public ExchangeAuthCode(String code, String clientId, String clientSecret) {
        this.code = code;
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
            put("code", code);
            put("grant_type", grantType);
            put("client_id", clientId);
            put("client_secret", clientSecret);
        }};
    }

    public final AccessToken send() {
        return super.send(AccessToken.class);
    }
}
