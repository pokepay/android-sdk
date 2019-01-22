package jp.pokepay.pokepaylib.BankAPI.User;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.PaginatedTransactions;
import jp.pokepay.pokepaylib.SendRequest;

public class GetUserTransactions {
    public String id;
    public String before;
    public String after;
    public int perPage;

    private Constants constants = new Constants();

    public GetUserTransactions(String id, String before, String after, int perPage){
        this.id = id;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    public PaginatedTransactions procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        PaginatedTransactions paginatedTransactions = (PaginatedTransactions)sendRequest.proc(new PaginatedTransactions(), "GET", null, "Authorization", str);
        return paginatedTransactions;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/users/" + id + "/transactions";

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
