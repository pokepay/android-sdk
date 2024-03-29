package jp.pokepay.pokepaylib.BankAPI.Account;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.PaginatedTransactions;

public class GetAccountTransactions extends BankRequest {
    @NonNull
    public String id;
    public String before;
    public String after;
    public int perPage = 30;

    @Deprecated
    public GetAccountTransactions(@NonNull String id, String before, String after, Integer perPage) {
        this.id = id;
        this.before = before;
        this.after = after;
        if (perPage != null) {
            this.perPage = perPage;
        }
    }

    public GetAccountTransactions(@NonNull String id, String before, String after, int perPage) {
        this.id = id;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    public GetAccountTransactions(@NonNull String id, String before, String after) {
        this.id = id;
        this.before = before;
        this.after = after;
    }

    protected final String path() {
        return "/accounts/" + id + "/transactions";
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
