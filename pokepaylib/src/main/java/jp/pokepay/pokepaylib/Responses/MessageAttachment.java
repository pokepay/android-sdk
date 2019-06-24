package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MessageAttachment {
    public double money_amount;
    public double point_amount;
    public boolean is_received;
    public Date expires_at;

    public MessageAttachment(){
        expires_at = new Date();
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
