package jp.pokepay.pokepaylib.BankAPI.Account;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.PaginatedCoupons;

public class GetAccountCoupons extends BankRequest {

    @NonNull
    public String accountId;
    public boolean isAvailable;
    public String before;
    public String after;
    public int perPage;

    public GetAccountCoupons(String accountId, boolean isAvailable, String before, String after, int perPage) {
        this.accountId = accountId;
        this.isAvailable = isAvailable;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    @Override
    protected String path() {
        return "/accounts/" + accountId + "/coupons";
    }

    @Override
    protected final Request.Method method() {
        return Request.Method.GET;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("is_available", isAvailable);
            put("before", before);
            put("after", after);
            put("per_page", perPage);
        }};
    }

    public final PaginatedCoupons send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(PaginatedCoupons.class, accessToken);
    }
}
