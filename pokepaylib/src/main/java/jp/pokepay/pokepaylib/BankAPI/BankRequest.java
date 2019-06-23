package jp.pokepay.pokepaylib.BankAPI;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Request;

public abstract class BankRequest {

    private final Constants constants = new Constants();

    protected abstract String path();
    protected abstract Request.Method method();

    protected String body() {
        return null;
    }

    protected <T> T send(Class<T> cls, String accessToken) {
        String url = constants.API_BASE_URL + path();
        String auth = constants.AUTHORIZATION + accessToken;
        T response = Request.send(cls, url, method(), body(), "Authorization", auth);
        return response;
    }

}
