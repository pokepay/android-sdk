package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class User extends Response {
    public String id;
    public String name;
    public String is_merchant;

    public User() {}
}
