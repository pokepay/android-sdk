package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserTransaction {
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

    public String toString() {
        try {
            return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
