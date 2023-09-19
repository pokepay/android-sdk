package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class IdentificationResult extends Response {
    @NonNull
    public boolean is_valid;
    public String identified_at;
    @NonNull
    public IdentificationMatch match;
}