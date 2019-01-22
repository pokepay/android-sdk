package jp.pokepay.pokepaylib.OAuthAPI;


import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.AccessToken;
import jp.pokepay.pokepaylib.SendRequest;

public class ExchangeAuthCode {
    public String code;
    public String grantType = "authorization_code";
    public String clientId;
    public String clientSecret;

    private Constants constants = new Constants();

    public ExchangeAuthCode(String code, String clientId, String clientSecret){
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public AccessToken procSend(){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        AccessToken accessToken = (AccessToken)sendRequest.proc(new AccessToken(), "POST", makeJson());
        return accessToken;
    }

    private String makeURL(){
        String str = constants.WWW_BASE_URL;
        str += "/oauth/token";

        return str;
    }

    private String makeJson() {
        String str = "{\"code\":\"" + code;
        str += "\", \"grant_type\":\"" + grantType;
        str += "\", \"client_id\":\"" + clientId;
        str += "\", \"client_secret\":\"" + clientSecret;
        str += "\"}";
        return str;
    }
}
