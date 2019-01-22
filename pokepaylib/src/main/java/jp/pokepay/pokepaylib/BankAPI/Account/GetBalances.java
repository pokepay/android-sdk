package jp.pokepay.pokepaylib.BankAPI.Account;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.PaginatedAccountBalances;
import jp.pokepay.pokepaylib.SendRequest;

public class GetBalances {
    public String id;
    public String before;
    public String after;
    public int perPage;

    private Constants constants = new Constants();

    public GetBalances(String id, String before, String after, int perPage){
        this.id = id;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    public PaginatedAccountBalances procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        PaginatedAccountBalances paginatedAccountBalances = (PaginatedAccountBalances) sendRequest.proc(new PaginatedAccountBalances(), "GET", null, "Authorization", str);
        return paginatedAccountBalances;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/accounts/" + id + "/balances";

        return url;
    }

    private String makeJson(){
        boolean flag = false;
        String str = "{";
        if(before != null){
            str += "\"before\":\"" + before;
            flag = true;
        }
        if(after != null) {
            if(flag){
                str += "\", ";
            }
            str += "\"after\":\"" + after;
            flag = true;
        }
        if(perPage >= 0) {
            if(flag){
                str += "\", ";
            }
            str += "\"per_page\":\"" + perPage;
            flag = true;
        }

        if(flag) {
            str += "\"}";
        }
        else{
            str += "}";
        }
        return str;
    }
}
