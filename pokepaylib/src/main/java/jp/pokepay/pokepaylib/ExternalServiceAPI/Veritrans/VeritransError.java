package jp.pokepay.pokepaylib.ExternalServiceAPI.Veritrans;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class VeritransError extends Response {
    @NonNull
    public String status;
    @NonNull
    public String code;
    @NonNull
    public String message;
}
