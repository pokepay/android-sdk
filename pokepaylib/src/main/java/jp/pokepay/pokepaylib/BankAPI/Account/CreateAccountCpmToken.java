package jp.pokepay.pokepaylib.BankAPI.Account;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.Parameters.Metadata;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.AccountCpmToken;

public class CreateAccountCpmToken extends BankRequest {

    public static final int SCOPE_PAYMENT = 1;
    public static final int SCOPE_TOPUP = 2;
    public static final int SCOPE_BOTH = 3;

    @NonNull
    public String accountId;
    public int scopes;
    public int expiresIn = 1;
    public Metadata metadata;

    @Deprecated
    public CreateAccountCpmToken(@NonNull String accountId, int scopes, Integer expiresIn, Metadata metadata) {
        this.accountId = accountId;
        this.scopes = scopes;
        if (expiresIn != null) {
            this.expiresIn = expiresIn;
        }
        this.metadata = metadata;
    }

    public CreateAccountCpmToken(@NonNull String accountId, int scopes, int expiresIn, Metadata metadata) {
        this.accountId = accountId;
        this.scopes = scopes;
        this.expiresIn = expiresIn;
        this.metadata = metadata;
    }

    public CreateAccountCpmToken(@NonNull String accountId, int scopes) {
        this.accountId = accountId;
        this.scopes = scopes;
    }

    public CreateAccountCpmToken(@NonNull String accountId, int scopes, Metadata metadata) {
        this.accountId = accountId;
        this.scopes = scopes;
        this.metadata = metadata;
    }

    protected final String path() {
        return "/accounts/" + accountId + "/cpm";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            ArrayList<String> scopesArray = new ArrayList<String>();
            if ((scopes & SCOPE_PAYMENT) != 0) scopesArray.add("payment");
            if ((scopes & SCOPE_TOPUP) != 0) scopesArray.add("topup");
            put("scopes", scopesArray);
            put("expires_in", expiresIn);
            if (metadata.map != null) {
                put("metadata", toJsonString(metadata.map));
            }
        }};
    }

    public final AccountCpmToken send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(AccountCpmToken.class, accessToken);
    }

    private String toJsonString(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append("\"");
            stringBuilder.append(entry.getKey());
            stringBuilder.append("\":\"");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("\"");
        }

        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}