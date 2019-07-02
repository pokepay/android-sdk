package jp.pokepay.pokepaylib.MessagingAPI;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Responses.PaginatedMessages;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class ListMessages extends BankRequest {
    public String before;
    public String after;
    public int perPage;

    public ListMessages(String before, String after, int perPage) {
        this.before = before;
        this.after = after;
        this.perPage = perPage;
    }

    protected final String path() {
        return "/messages";
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

    public final PaginatedMessages send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(PaginatedMessages.class, accessToken);
    }
}
