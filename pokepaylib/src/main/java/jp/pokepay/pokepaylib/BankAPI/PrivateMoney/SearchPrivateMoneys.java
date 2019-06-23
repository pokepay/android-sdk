package jp.pokepay.pokepaylib.BankAPI.PrivateMoney;

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
        return "/private-moneys" + (
            QueryString.build(new String[][]{
                    {"name", name},
                    {"include_exclusive", Boolean.toString(includeExclusive)},
                    {"before", before},
                    {"after", after},
                    {"per_page", perPage > 0 ? Integer.toString(perPage) : null },
                })
            );
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public PaginatedPrivateMoney send(String accessToken) {
        return super.send(PaginatedPrivateMoney.class, accessToken);
    }
}
