package jp.pokepay.pokepaylib.BankAPI;

import java.util.HashMap;
import java.util.Map;
import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Request;

public abstract class BankRequest {

    private final static Constants constants = new Constants();

    protected abstract String path();
    protected abstract Request.Method method();

    protected Map<String, Object> parameters() {
        return null;
    }

    protected <T> T send(Class<T> cls, String accessToken) {
        String url = constants.API_BASE_URL + path();
        String auth = constants.AUTHORIZATION + accessToken;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", auth);
        T response = Request.send(cls, url, method(), parameters(), headers);
        return response;
    }

}
