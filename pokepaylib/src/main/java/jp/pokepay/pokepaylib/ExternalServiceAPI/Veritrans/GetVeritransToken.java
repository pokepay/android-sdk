package jp.pokepay.pokepaylib.ExternalServiceAPI.Veritrans;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequestError;
import jp.pokepay.pokepaylib.ExternalServiceAPI.ExternalServiceRequest;
import jp.pokepay.pokepaylib.ExternalServiceAPI.ExternalServiceRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.VeritransToken;

public class GetVeritransToken extends ExternalServiceRequest {
    @NonNull
    public String cardNumber;
    @NonNull
    public String cardExpiryDate;
    @NonNull
    public String securityCode;
    @NonNull
    public String tokenApiKey;
    @NonNull
    public String cardholderName

    public GetVeritransToken(@NonNull String cardNumber, @NonNull String cardExpiryDate, @NonNull String securityCode, @NonNull String tokenApiKey, @NonNull String cardholderName) {
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.securityCode = securityCode;
        this.tokenApiKey = tokenApiKey;
        this.cardholderName = cardholderName;
    }

    protected final String path() {
        return "https://api3.veritrans.co.jp/4gtoken";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("card_number", cardNumber);
            put("card_expire", cardExpiryDate);
            put("security_code", securityCode);
            put("token_api_key", tokenApiKey);
            put("cardholder_name", cardholderName);
        }};
    }

    public final VeritransToken send() throws ProcessingError, ExternalServiceRequestError {
        try {
            return Request.send(VeritransToken.class, VeritransRequestError.class, path(), method(), parameters());
        } catch (OAuthRequestError | BankRequestError e) {
            throw new RuntimeException("PANIC! This must not be happened");
        }
    }
}
