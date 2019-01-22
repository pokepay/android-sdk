package jp.pokepay.pokepaylib.MessagingAPI;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.PaginatedMessages;
import jp.pokepay.pokepaylib.SendRequest;

public class ListMessages {
    public String before;
    public String after;
    public int perPage;

    private Constants constants = new Constants();

    public ListMessages(String before, String after, int perPage){
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    public PaginatedMessages procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        PaginatedMessages paginatedMessages = (PaginatedMessages) sendRequest.proc(new PaginatedMessages(), "GET", null, "Authorization", str);
        return paginatedMessages;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/messages";

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
