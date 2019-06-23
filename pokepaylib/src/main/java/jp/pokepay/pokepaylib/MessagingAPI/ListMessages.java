package jp.pokepay.pokepaylib.MessagingAPI;

import jp.pokepay.pokepaylib.Responses.PaginatedMessages;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class ListMessages extends BankRequest {
    public String before;
    public String after;
    public int perPage;

    public ListMessages(String before, String after, int perPage) {
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    protected final String path() {
        return "/messages";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    protected final String body() {
        // FIXME: FUCK
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

    public final PaginatedMessages send(String accessToken) {
        return super.send(PaginatedMessages.class, accessToken);
    }
}
