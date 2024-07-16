package jp.pokepay.pokepaylib.BankAPI.User;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.PaginatedCreditCards;

public class GetUserCards extends BankRequest {
    @NonNull
    public String id;
    public String organizationCode;
    public String before;
    public String after;
    public int perPage;

    public GetUserCards(@NonNull String id, String organizationCode, String before, String after, int perPage) {
        this.id = id;
        this.organizationCode = organizationCode;
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    public GetUserCards(@NonNull String id, String organizationCode, int per_page) {
        this(id, organizationCode, null, null, per_page);
    }

    protected final String path() {
        return "/users/" + id + "/cards";
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("organization_code", organizationCode);
            put("before", before);
            put("after", after);
            put("per_page", perPage);
        }};
    }

    public final PaginatedCreditCards send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(PaginatedCreditCards.class, accessToken);
    }
}
