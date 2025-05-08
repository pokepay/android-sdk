package jp.pokepay.pokepaylib.OAuthAPI;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.BuildConfig;
import jp.pokepay.pokepaylib.Env;
import jp.pokepay.pokepaylib.ExternalServiceAPI.ExternalServiceRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;

public abstract class OAuthRequest {

    protected abstract String path();
    protected abstract Request.Method method();

    protected Map<String, Object> parameters() {
        return null;
    }

    protected <T> T send(Class<T> cls) throws ProcessingError, OAuthRequestError {
        String url = Env.current().WWW_BASE_URL() + path();
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("X-SDK-Version", BuildConfig.VERSION_NAME);
        try {
            return Request.send(cls, OAuthRequestError.class, url, method(), parameters(), headers);
        } catch (BankRequestError | ExternalServiceRequestError e) {
            throw new RuntimeException("PANIC! This must not be happened");
        }
    }

}
