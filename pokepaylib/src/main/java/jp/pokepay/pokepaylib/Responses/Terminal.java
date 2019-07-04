package jp.pokepay.pokepaylib.Responses;

import jp.pokepay.pokepaylib.Response;

public class Terminal extends Response {
    public String  id;
    public String  name;
    public String  hardware_id;
    public String  push_service;
    public String  push_token;
    public User    user;
    public Account account;
}
