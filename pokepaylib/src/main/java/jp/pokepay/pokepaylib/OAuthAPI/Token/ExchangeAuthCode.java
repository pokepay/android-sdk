package jp.pokepay.pokepaylib.OAuthAPI.Token;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequest;
import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.AccessToken;

public class ExchangeAuthCode extends OAuthRequest {
    @NonNull
    public String code;
    @NonNull
    public String grantType = "authorization_code";
    @NonNull
    public String clientId;
    @NonNull
    public String clientSecret;

    public ExchangeAuthCode(String code, String clientId, String clientSecret, String grantType) {
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        if (grantType != null) {
            this.grantType = grantType;
        }
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

    public final AccessToken send() throws ProcessingError, OAuthRequestError {
        return super.send(AccessToken.class);
    }
}
