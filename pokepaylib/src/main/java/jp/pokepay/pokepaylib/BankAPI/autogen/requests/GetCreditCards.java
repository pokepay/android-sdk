// DO NOT EDIT: File is generated by code generator.
package jp.pokepay.pokepaylib.BankAPI.autogen.requests;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.autogen.responses.*;

public class GetCreditCards extends BankRequest {
    private String userId;
    private String before;
    private String after;
    private Integer perPage;
    private String organizationCode;

    public GetCreditCards(String userId, String organizationCode) {
        this.userId = userId;
        this.organizationCode = organizationCode;
    }

    public GetCreditCards before(String before) {
        this.before = before;
        return this;
    }

    public GetCreditCards after(String after) {
        this.after = after;
        return this;
    }

    public GetCreditCards perPage(Integer perPage) {
        this.perPage = perPage;
        return this;
    }

    @Override
    protected final Request.Method method() {
        return Request.Method.GET;
    }

    @Override
    public String path() {
        return "/users" + "/" + this.userId + "/cards";
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            if (before != null) {
                put("before", before);
            }
            if (after != null) {
                put("after", after);
            }
            if (perPage != null) {
                put("per_page", perPage);
            }
            if (organizationCode != null) {
                put("organization_code", organizationCode);
            }
        }};
    }

    public final PaginatedCreditCards send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(PaginatedCreditCards.class, accessToken);
    }
}
