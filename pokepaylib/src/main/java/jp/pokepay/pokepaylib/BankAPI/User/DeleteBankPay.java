package jp.pokepay.pokepaylib.BankAPI.User;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.NoContent;

public class DeleteBankPay extends BankRequest {
    @NonNull
    public String id;
    @NonNull
    public String bankId;

    public DeleteBankPay(@NonNull String id, @NonNull String bankId) {
        this.id = id;
        this.bankId = bankId;
    }

    protected final String path() {
        return "/users/" + id + "/banks";
    }

    protected final Request.Method method() {
        return Request.Method.DELETE;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("bank_id", bankId);
        }};
    }

    public final NoContent send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(NoContent.class, accessToken);
    }
}
