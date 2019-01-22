package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class MessageUnreadCount {
    @JsonProperty("count")
    int count;

    public MessageUnreadCount(){
    }

    public void print(){
        System.out.println("MessageUnreadCount(");
        System.out.println("count: \"" + count + "\",");
        System.out.println("),");
    }
}
