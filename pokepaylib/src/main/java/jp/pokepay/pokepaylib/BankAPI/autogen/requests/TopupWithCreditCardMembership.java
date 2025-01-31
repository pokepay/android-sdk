// DO NOT EDIT: File is generated by code generator.
package jp.pokepay.pokepaylib.BankAPI.autogen.requests;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;

public class TopupWithCreditCardMembership extends BankRequest {
    private String userId;
    private String cardRegisteredAt;
    private String accountId;
    private Integer amount;
    private Boolean deleteCardIfAuthFail;
    private String organizationCode;
    private String requestId;

    public TopupWithCreditCardMembership(String userId, String cardRegisteredAt, String accountId, Integer amount, String organizationCode) {
        this.userId = userId;
        this.cardRegisteredAt = cardRegisteredAt;
        this.accountId = accountId;
        this.amount = amount;
        this.organizationCode = organizationCode;
    }

    public TopupWithCreditCardMembership deleteCardIfAuthFail(Boolean deleteCardIfAuthFail) {
        this.deleteCardIfAuthFail = deleteCardIfAuthFail;
        return this;
    }

    public TopupWithCreditCardMembership requestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    @Override
    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    public String path() {
        return "/veritrans" + "/card-authorize" + "/topup-with-membership";
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            if (userId != null) {
                put("user_id", userId);
            }
            if (cardRegisteredAt != null) {
                put("card_registered_at", cardRegisteredAt);
            }
            if (accountId != null) {
                put("account_id", accountId);
            }
            if (amount != null) {
                put("amount", amount);
            }
            if (deleteCardIfAuthFail != null) {
                put("delete_card_if_auth_fail", deleteCardIfAuthFail);
            }
            if (organizationCode != null) {
                put("organization_code", organizationCode);
            }
            if (requestId != null) {
                put("request_id", requestId);
            }
        }};
    }

    public final String send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(String.class, accessToken);
    }
}
