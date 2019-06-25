package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Cashtray extends Response {
    public String id;
    public double amount;
    public String description;
    public User user;
    public PrivateMoney private_money;
    public String expires_at;
    public String token;

    public Cashtray() {
        user = new User();
        private_money = new PrivateMoney();
    }
}
