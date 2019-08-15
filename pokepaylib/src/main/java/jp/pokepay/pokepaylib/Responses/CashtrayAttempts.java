package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class CashtrayAttempts extends Response {
    @NonNull
    public CashtrayAttempt[] rows;
}
