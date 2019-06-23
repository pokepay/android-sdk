package jp.pokepay.pokepaylib.BankAPI.Account;

import jp.pokepay.pokepaylib.Responses.PaginatedTransactions;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.QueryString;

public class GetAccountTransactions extends BankRequest {
    public String id;
    public String before;
    public String after;
    public int perPage;

    public GetAccountTransactions(String id, String before, String after, int perPage) {
        this.id = id;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    protected final String path() {
        return "/accounts/" + id + "/transactions" + (
                QueryString.build(new String[][]{
                        {"before", before},
                        {"after", after},
                        {"per_page", perPage > 0 ? Integer.toString(perPage) : null},
                })
        );
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    protected final String parameters() {
        return null;
    }

    public final PaginatedTransactions send(String accessToken) {
        return super.send(PaginatedTransactions.class, accessToken);
    }
}
