package jp.pokepay.pokepaylib.PartnerAPI;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.BuildConfig;
import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;

public abstract class ExternalServiceRequest {

    protected abstract String path();
    protected abstract Request.Method method();

    protected Map<String, Object> parameters() {
        return null;
    }

    protected <T> T send(Class<T> cls) throws ProcessingError, ExternalServiceRequestError {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("X-SDK-Version", BuildConfig.VERSION_NAME);
        try {
            return Request.send(cls, ExternalServiceRequestError.class, path(), method(), parameters(), headers);
        } catch (BankRequestError | OAuthRequestError e) {
            throw new RuntimeException("PANIC! This must not be happened");
        }
    }

}
