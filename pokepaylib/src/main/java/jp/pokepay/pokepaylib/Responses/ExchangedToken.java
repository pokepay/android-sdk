package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jp.pokepay.pokepaylib.Response;

public class ExchangedToken extends Response {
    @NonNull
    public String access_token;
    @NonNull
    public String issued_token_type;
    @NonNull
    public String token_type;
    public int    expires_in;
    public String scope;
    public String refresh_token;

    public List<String> getScopes() {
        if (scope == null || scope.isEmpty()) return Collections.emptyList();
        return Arrays.asList(scope.split(" "));
    }
}
