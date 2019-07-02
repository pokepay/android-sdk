package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PaginatedMessages extends Response {
    public int per_page;
    public int count;
    public String next;
    public String prev;
    public Message items[];

    public PaginatedMessages(){
        this.items = new Message[]{};
    }
}
