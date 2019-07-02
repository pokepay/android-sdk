package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PaginatedAccounts extends Response {
    public int     per_page;
    public int     count;
    public String  next;
    public String  prev;
    public Account items[];

    public PaginatedAccounts(){
        items = new Account[]{};
    }
}
