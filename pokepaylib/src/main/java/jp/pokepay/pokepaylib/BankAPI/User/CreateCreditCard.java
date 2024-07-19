package jp.pokepay.pokepaylib.BankAPI.User;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.CreditCard;

public class CreateCreditCard extends BankRequest {
    @NonNull
    public String id;
    @NonNull
    public String token;
    public String organizationCode;

    public CreateCreditCard(@NonNull String id, @NonNull String token, String organizationCode) {
        this.id = id;
        this.token = token;
        this.organizationCode = organizationCode;
    }

    protected final String path() {
        return "/users/" + id + "/cards";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("token", token);
            if (organizationCode != null) {
                put("organization_code", organizationCode);
            }
        }};
    }

    public final CreditCard send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(CreditCard.class, accessToken);
    }
}
