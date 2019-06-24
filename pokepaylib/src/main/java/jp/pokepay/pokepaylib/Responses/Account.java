package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Account {
    public String       id;
    public String       name;
    public double       balance;
    public double       money_balance;
    public double       point_balance;
    public boolean      is_suspended;
    public PrivateMoney private_money;
    public String       nearest_expires_at;

    public Account(){
        private_money = new PrivateMoney();
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
