package jp.pokepay.pokepaylib.BankAPI.PrivateMoney;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.PaginatedPrivateMoney;
import jp.pokepay.pokepaylib.SendRequest;

public class SearchPrivateMoney {
    public String name;
    public boolean includeExclusive;
    public String before;
    public String after;
    public int perPage;

    private Constants constants = new Constants();

    public SearchPrivateMoney(String name, boolean includeExclusive, String before, String after, int perPage){
        this.name = name;
        this.includeExclusive = includeExclusive;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    public PaginatedPrivateMoney procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        PaginatedPrivateMoney paginatedPrivateMoney = (PaginatedPrivateMoney) sendRequest.proc(new PaginatedPrivateMoney(), "GET", null, "Authorization", str);
        return paginatedPrivateMoney;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/private-moneys";
        url += "?";
        if(name != null){
            url += "name=" + name;
            url += "&";
        }
        url += "include_exclusive=" + includeExclusive;
        url += "&";
        if(before != null){
            url += "before=" + before;
            url += "&";
        }
        if(after != null){
            url += "after=" + after;
            url += "&";
        }
        url += "per_page=" + perPage;
        return url;
    }

    private String makeJson(){
        String str = "{";
        str += "\"include_exclusive\":" + includeExclusive;
        if(before != null){
            str += "\", ";
            str += "\"before\":\"" + before;
        }
        if(after != null) {
            str += "\", ";
            str += "\"after\":\"" + after;
        }
        if(perPage >= 0) {
            str += "\", ";
            str += "\"per_page\":\"" + perPage;
        }
        str += "\"}";
        return str;
    }
}
