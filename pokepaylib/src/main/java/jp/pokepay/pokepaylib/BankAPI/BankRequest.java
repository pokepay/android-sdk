package jp.pokepay.pokepaylib.BankAPI;

import java.util.HashMap;
import java.util.Map;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Env;

public abstract class BankRequest {

    private final Env env = Env.current();

    protected abstract String path();
    protected abstract Request.Method method();

    protected Map<String, Object> parameters() {
        return null;
    }

    protected <T> T send(Class<T> cls, String accessToken) {
        String url = env.API_BASE_URL() + path();
        String auth = "Bearer " + accessToken;
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", auth);
        T response = Request.send(cls, url, method(), parameters(), headers);
        return response;
    }

}
