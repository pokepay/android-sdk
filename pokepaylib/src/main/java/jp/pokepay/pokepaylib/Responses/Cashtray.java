package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

public class Cashtray extends Response {
    @NonNull
    public String id;
    @NonNull
    public Double amount;
    @NonNull
    public String description;
    @NonNull
    public User user;
    @NonNull
    public PrivateMoney private_money;
    @NonNull
    public Date expires_at;
    public Date canceled_at;
    @NonNull
    public String token;
    public CashtrayAttempt attempt;
    public UserTransaction transaction;
}
