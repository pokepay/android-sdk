// DO NOT EDIT: File is generated by code generator.
package jp.pokepay.pokepaylib.BankAPI.autogen.requests;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.NoContent;

public class DeleteCreditCard extends BankRequest {
    private String cardRegisteredAt;
    private String organizationCode;
    private String userId;

    public DeleteCreditCard(String userId, String cardRegisteredAt, String organizationCode) {
        this.userId = userId;
        this.cardRegisteredAt = cardRegisteredAt;
        this.organizationCode = organizationCode;
    }

    @Override
    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    public String path() {
        return "/users" + "/" + this.userId + "/cards" + "/delete";
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            if (cardRegisteredAt != null) {
                put("card_registered_at", cardRegisteredAt);
            }
            if (organizationCode != null) {
                put("organization_code", organizationCode);
            }
        }};
    }

    public final NoContent send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(NoContent.class, accessToken);
    }
}
