package jp.pokepay.pokepaylib.OAuthAPI.Token;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequest;
import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.ExchangedToken;

// (permits "grant_type" "client_id" "client_secret" "resource" "audience"
//    "scope" "requested_token_type" "subject_token" "subject_token_type"
//    "actor_token_type" "actor_token")


public class TokenExchange extends OAuthRequest {
    @NonNull
    public String grantType = "urn:ietf:params:oauth:grant-type:token-exchange";
    public String clientId;
    public String clientSecret;
    public String resource;
    public String audience;
    @NonNull
    public String subjectToken;
    @NonNull
    public String subjectTokenType;
    public String requestedTokenType;
    public List<String> scopes;
    public String actorTokenType;
    public String actorToken;


    public TokenExchange(@NonNull String subjectToken, @NonNull String subjectTokenType) {
        this.subjectToken = subjectToken;
        this.subjectTokenType = subjectTokenType;
    }

    public TokenExchange clientId(String v)           { this.clientId = v; return this; }
    public TokenExchange clientSecret(String v)       { this.clientSecret = v; return this; }
    public TokenExchange resource(String v)           { this.resource = v; return this; }
    public TokenExchange audience(String v)           { this.audience = v; return this; }
    public TokenExchange requestedTokenType(String v) { this.requestedTokenType = v; return this; }
    public TokenExchange scopes(List<String> v)       { this.scopes = v; return this; }
    public TokenExchange actorToken(String v)         { this.actorToken = v; return this; }
    public TokenExchange actorTokenType(String v)     { this.actorTokenType = v; return this; }

    protected final String path() {
        return "/oauth/token";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {
            void p(String key, Object value) { if (value != null) put(key, value); }
            {
                p("grant_type", grantType);
                p("client_id", clientId);
                p("client_secret", clientSecret);
                p("resource", resource);
                p("audience", audience);
                p("scope", scopes != null ? String.join(" ", scopes) : null);
                p("requested_token_type", requestedTokenType);
                p("subject_token", subjectToken);
                p("subject_token_type", subjectTokenType);
                p("actor_token_type", actorTokenType);
                p("actor_token", actorToken);
            }};
    }

    public final ExchangedToken send() throws ProcessingError, OAuthRequestError {
        return super.send(ExchangedToken.class);
    }
}
