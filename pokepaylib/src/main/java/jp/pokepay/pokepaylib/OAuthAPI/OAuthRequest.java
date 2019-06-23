package jp.pokepay.pokepaylib.OAuthAPI;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Request;

public abstract class OAuthRequest {

    private final Constants constants = new Constants();

    protected abstract String path();
    protected abstract Request.Method method();

    protected String body() {
        return null;
    }

    protected <T> T send(Class<T> cls) {
        String url = constants.WWW_BASE_URL + path();
        T response = Request.send(cls, url, method(), body());
        return response;
    }

}
