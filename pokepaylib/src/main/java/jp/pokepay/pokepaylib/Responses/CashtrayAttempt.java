package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

public class CashtrayAttempt extends Response {
    @NonNull
    public User user;
    public Account account;
    @NonNull
    public int status_code;
    @NonNull
    public String error_type;
    @NonNull
    public String error_message;
    @NonNull
    public Date created_at;
    public String strategy;
}
