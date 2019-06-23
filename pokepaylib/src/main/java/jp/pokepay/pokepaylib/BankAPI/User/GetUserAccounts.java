package jp.pokepay.pokepaylib.BankAPI.User;

import jp.pokepay.pokepaylib.Responses.PaginatedAccounts;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class GetUserAccounts extends BankRequest {
    public String id;
    public String before;
    public String after;
    public int perPage;

    public GetUserAccounts(String id, String before, String after, int perPage) {
        this.id = id;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    protected final String path() {
        return "/users/" + id + "/accounts";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    protected final String body() {
        // FIXME: fuck
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

    public final PaginatedAccounts send(String accessToken) {
        return super.send(PaginatedAccounts.class, accessToken);
    }
}
