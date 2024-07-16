package jp.pokepay.pokepaylib.PartnerAPI;

import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;

public abstract class PartnerRequest {

    protected abstract String path();
    protected abstract Request.Method method();

    protected Map<String, Object> parameters() {
        return null;
    }

    protected <T> T send(Class<T> cls) throws ProcessingError, PartnerRequestError {
        try {
            return Request.send(cls, PartnerRequestError.class, path(), method(), parameters());
        } catch (BankRequestError | OAuthRequestError e) {
            throw new RuntimeException("PANIC! This must not be happened");
        }
    }

}
