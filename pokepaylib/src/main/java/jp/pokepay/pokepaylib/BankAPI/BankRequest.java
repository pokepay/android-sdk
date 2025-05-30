package jp.pokepay.pokepaylib.BankAPI;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BuildConfig;
import jp.pokepay.pokepaylib.Env;
import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequestError;
import jp.pokepay.pokepaylib.ExternalServiceAPI.ExternalServiceRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;

public abstract class BankRequest {

    protected abstract String path();
    protected abstract Request.Method method();

    protected Map<String, Object> parameters() {
        return null;
    }

    protected <T> T send(Class<T> cls, String accessToken) throws ProcessingError, BankRequestError {
        String url = Env.current().API_BASE_URL() + path();
        String auth = "Bearer " + accessToken;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", auth);
        headers.put("X-SDK-Version", BuildConfig.VERSION_NAME);
        try {
            return Request.send(cls, BankRequestError.class, url, method(), parameters(), headers);
        } catch (OAuthRequestError | ExternalServiceRequestError error) {
            throw new RuntimeException("PANIC! This must not be happened");
        }
    }

}
