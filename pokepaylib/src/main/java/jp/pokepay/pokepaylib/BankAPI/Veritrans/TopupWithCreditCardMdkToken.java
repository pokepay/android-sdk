package jp.pokepay.pokepaylib.BankAPI.Veritrans;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;

public class TopupWithCreditCardMdkToken extends BankRequest {
    @NonNull
    public String userId;
    @NonNull
    public String token;
    @NonNull
    public String accountId;
    @NonNull
    public int amount;
    public String organizationCode;

    TopupWithCreditCardMdkToken(@NonNull String userId, @NonNull String token, @NonNull String accountId, @NonNull int amount, String organizationCode) {
        this.userId = userId;
        this.token = token;
        this.accountId = accountId;
        this.amount = amount;
        this.organizationCode= organizationCode;
    }

    protected final String path() {
        return "/veritrans/card-authorize/topup-with-mdk-token";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("user_id", userId);
            put("token", token);
            put("account_id", accountId);
            put("amount", amount);

            if (organizationCode != null) {
                put("organization_code", organizationCode);
            }
        }};
    }

    public final String send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(String.class, accessToken);
    }

}
