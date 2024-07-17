package jp.pokepay.pokepaylib.PartnerAPI.Veritrans;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.PartnerAPI.PartnerRequest;
import jp.pokepay.pokepaylib.PartnerAPI.PartnerRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.VeritransToken;

public class GetVeritransToken extends PartnerRequest {
    @NonNull
    public String cardNumber;
    @NonNull
    public String cardExpiryDate;
    @NonNull
    public String securityCode;
    @NonNull
    public String tokenApiKey;

    public GetVeritransToken(@NonNull String cardNumber, @NonNull String cardExpiryDate, @NonNull String securityCode, @NonNull String tokenApiKey) {
        this.cardNumber = cardNumber;
        this.cardExpiryDate = cardExpiryDate;
        this.securityCode = securityCode;
        this.tokenApiKey = tokenApiKey;
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
        }};
    }

    public final VeritransToken send() throws ProcessingError, PartnerRequestError {
        return super.send(VeritransToken.class);
    }
}
