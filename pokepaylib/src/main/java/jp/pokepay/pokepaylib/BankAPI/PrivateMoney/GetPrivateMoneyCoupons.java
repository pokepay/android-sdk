package jp.pokepay.pokepaylib.BankAPI.PrivateMoney;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.PaginatedCoupons;

public class GetPrivateMoneyCoupons extends BankRequest {
    @NonNull
    public String privateMoneyId;
    public String before;
    public String after;
    public int perPage;

    public GetPrivateMoneyCoupons(String privateMoneyId, String before, String after, int perPage) {
        this.privateMoneyId = privateMoneyId;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    @Override
    protected String path() {
        return "/private-moneys/" + privateMoneyId + "/coupons";
    }

    @Override
    protected Request.Method method() {
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

    public final PaginatedCoupons send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(PaginatedCoupons.class, accessToken);
    }
}
