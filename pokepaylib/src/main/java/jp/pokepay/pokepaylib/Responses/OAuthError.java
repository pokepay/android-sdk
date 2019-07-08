package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class OAuthError extends Response {
    @NonNull
    public String error;
}
