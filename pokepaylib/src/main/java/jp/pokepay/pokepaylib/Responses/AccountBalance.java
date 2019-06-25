package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountBalance extends Response {
    public Date expires_at;
    public double money_amount;
    public double point_amount;

    public AccountBalance(){
        expires_at = new Date();
    }
}
