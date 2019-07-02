package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MessageAttachment extends Response {
    public double money_amount;
    public double point_amount;
    public boolean is_received;
    public Date expires_at;

    public MessageAttachment(){
        expires_at = new Date();
    }
}
