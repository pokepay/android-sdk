package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Terminal extends Response {
    public String  id;
    public String  name;
    public String  hardware_id;
    public String  push_service;
    public String  push_token;
    public User    user;
    public Account account;

    public Terminal(){
        user = new User();
        account = new Account();
    }
}
