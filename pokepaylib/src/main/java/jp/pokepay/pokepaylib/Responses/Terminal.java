package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class Terminal extends Response {
    @NonNull
    public String  id;
    @NonNull
    public String  name;
    @NonNull
    public String  hardware_id;
    public String  push_service;
    public String  push_token;
    @NonNull
    public User    user;
    @NonNull
    public Account account;
}
