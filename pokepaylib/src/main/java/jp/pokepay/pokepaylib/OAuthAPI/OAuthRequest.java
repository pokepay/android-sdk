package jp.pokepay.pokepaylib.OAuthAPI;

import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.Env;
import jp.pokepay.pokepaylib.PartnerAPI.PartnerRequestError;
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
        try {
            return Request.send(cls, OAuthRequestError.class, url, method(), parameters());
        } catch (BankRequestError | PartnerRequestError e) {
            throw new RuntimeException("PANIC! This must not be happened");
        }
    }

}
