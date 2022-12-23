package jp.pokepay.pokepaylib.BankAPI.PrivateMoney;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.PaginatedPrivateMoneys;

public class SearchPrivateMoneys extends BankRequest {
    public String name;
    public boolean includeExclusive = false;
    public String before;
    public String after;
    public int perPage = 30;

    public SearchPrivateMoneys(String name, boolean includeExclusive, String before, String after, int perPage) {
        this.name = name;
        this.includeExclusive = includeExclusive;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    public SearchPrivateMoneys(String name, String before, String after) {
        this.name = name;
        this.before = before;
        this.after = after;
    }

    public SearchPrivateMoneys(String name, String before, String after, boolean includeExclusive) {
        this.name = name;
        this.before = before;
        this.after = after;
        this.includeExclusive = includeExclusive;
    }

    public SearchPrivateMoneys(String name, String before, String after, int perPage) {
        this.name = name;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    protected final String path() {
        return "/private-moneys";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("name", name);
            put("include_exclusive", includeExclusive);
            put("before", before);
            put("after", after);
            put("per_page", perPage);
        }};
    }

    public PaginatedPrivateMoneys send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(PaginatedPrivateMoneys.class, accessToken);
    }
}
