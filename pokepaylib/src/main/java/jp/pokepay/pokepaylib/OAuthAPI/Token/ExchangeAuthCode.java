package jp.pokepay.pokepaylib.OAuthAPI.Token;

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

    protected final String body() {
        String str = "{\"code\":\"" + code;
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
