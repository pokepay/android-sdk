package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class User extends Response {
    @NonNull
    public String id;
    @NonNull
    public String name;
    public boolean is_merchant;
}
