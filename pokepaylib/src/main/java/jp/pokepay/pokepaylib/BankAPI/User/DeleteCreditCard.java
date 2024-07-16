package jp.pokepay.pokepaylib.BankAPI.User;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.NoContent;

public class DeleteCreditCard extends BankRequest {
    @NonNull
    public String id;
    @NonNull
    public Date cardRegisteredAt;
    public String organizationCode;

    public DeleteCreditCard(@NonNull String id, @NonNull Date cardRegisteredAt, String organizationCode) {
        this.id = id;
        this.cardRegisteredAt = cardRegisteredAt;
        this.organizationCode = organizationCode;
    }

    public DeleteCreditCard(@NonNull String id, @NonNull Date cardRegisteredAt) {
        this(id, cardRegisteredAt, null);
    }

    protected final String path() {
        return "/users/" + id + "/cards/delete";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("card_registered_at", cardRegisteredAt);
            if (organizationCode != null) {
                put("organization_code", organizationCode);
            }
        }};
    }

    public final NoContent send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(NoContent.class, accessToken);
    }
}
