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
    public int perPage = 30;

    @Deprecated
    public GetAccountCoupons(@NonNull String accountId, boolean isAvailable, String before, String after, Integer perPage) {
        this.accountId = accountId;
        this.isAvailable = isAvailable;
        this.before = before;
        this.after = after;
        if (perPage != null) {
            this.perPage = perPage;
        }
    }

    public GetAccountCoupons(@NonNull String accountId, boolean isAvailable, String before, String after, int perPage) {
        this.accountId = accountId;
        this.isAvailable = isAvailable;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    public GetAccountCoupons(@NonNull String accountId, boolean isAvailable, String before, String after) {
        this.accountId = accountId;
        this.after = after;
        this.before = before;
        this.isAvailable = isAvailable;
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
        Map<String, Object> map = new HashMap();
        map.put("is_available", isAvailable);
        if (before != null && !before.isEmpty()) map.put("before", before);
        if (after != null && !after.isEmpty()) map.put("after", after);
        map.put("per_page", perPage);
        return map;
    }

    public final PaginatedCoupons send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(PaginatedCoupons.class, accessToken);
    }
}
