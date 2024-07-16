package jp.pokepay.pokepaylib.BankAPI.Veritrans;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;

public class TopupWithCreditCardMembership extends BankRequest {
    @NonNull
    public String userId;
    @NonNull
    public Date cardRegisteredAt;
    @NonNull
    public String accountId;
    @NonNull
    public int amount;
    public Boolean deleteCardIfAuthFail;
    public String organizationCode;

    TopupWithCreditCardMembership(@NonNull String userId, @NonNull Date cardRegisteredAt, @NonNull String accountId, @NonNull int amount, Boolean deleteCardIfAuthFail, String organizationCode) {
       this.userId = userId;
       this.cardRegisteredAt = cardRegisteredAt;
       this.accountId = accountId;
       this.amount = amount;
       this.deleteCardIfAuthFail = deleteCardIfAuthFail;
       this.organizationCode = organizationCode;
    }

    protected final String path() {
        return "/veritrans/card-authorize/topup-with-membership";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("user_id", userId);
            put("card_registered_at", Request.getFormatter().format(cardRegisteredAt));
            put("account_id", accountId);
            put("amount", amount);

            if (deleteCardIfAuthFail != null) {
                put("delete_card_if_auth_fail", deleteCardIfAuthFail);
            }

            if (organizationCode != null) {
                put("organization_code", organizationCode);
            }
        }};
    }

    public final String send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(String.class, accessToken);
    }

}
