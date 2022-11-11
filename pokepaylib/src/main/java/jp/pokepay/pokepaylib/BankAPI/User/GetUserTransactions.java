package jp.pokepay.pokepaylib.BankAPI.User;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.PaginatedTransactions;

public class GetUserTransactions extends BankRequest {
    @NonNull
    public String id;
    public String before;
    public String after;
    public Integer perPage;

    public GetUserTransactions(@NonNull String id, String before, String after, Integer perPage) {
        this.id = id;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    protected final String path() {
        return "/users/" + id + "/transactions";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("before", before);
            put("after", after);
            put("per_page", perPage);
        }};
    }

    public final PaginatedTransactions send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(PaginatedTransactions.class, accessToken);
    }
}
