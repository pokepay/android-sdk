package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserTransaction extends Response {
    public String  id;
    public User    user;
    public double  balance;
    public double  amount;
    public double  money_amount;
    public double  point_amount;
    public Account account;
    public String  description;
    public String  done_at;
    public String  type;
    public boolean is_modified;

    public UserTransaction(){
        user = new User();
        account = new Account();
    }
}
