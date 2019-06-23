package jp.pokepay.pokepaylib.BankAPI.PrivateMoney;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.QueryString;
import jp.pokepay.pokepaylib.Responses.PaginatedPrivateMoney;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class SearchPrivateMoneys extends BankRequest {
    public String name;
    public boolean includeExclusive;
    public String before;
    public String after;
    public int perPage;

    public SearchPrivateMoneys(String name, boolean includeExclusive, String before, String after, int perPage) {
        this.name = name;
        this.includeExclusive = includeExclusive;
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
            put("per_page", perPage > 0 ? perPage : null);
        }};
    }

    public PaginatedPrivateMoney send(String accessToken) {
        return super.send(PaginatedPrivateMoney.class, accessToken);
    }
}
