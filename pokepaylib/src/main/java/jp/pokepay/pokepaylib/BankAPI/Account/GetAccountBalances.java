package jp.pokepay.pokepaylib.BankAPI.Account;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.Responses.PaginatedAccountBalances;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class GetAccountBalances extends BankRequest {
    public String id;
    public String before;
    public String after;
    public int perPage;

    public GetAccountBalances(String id, String before, String after, int perPage) {
        this.id = id;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    protected final String path() {
        return "/accounts/" + id + "/balances";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("before", before);
            put("after", after);
            put("per_page", perPage > 0 ? perPage : null);
        }};
    }

    public final PaginatedAccountBalances send(String accessToken) {
        return super.send(PaginatedAccountBalances.class, accessToken);
    }
}
