package jp.pokepay.pokepaylib.BankAPI.User;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.SendRequest;

public class RegisterUserEmail {
    public String token;

    private Constants constants = new Constants();

    public RegisterUserEmail(String token){
        this.token = token;
    }

    public String procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        String ret = (String)sendRequest.proc(new String(), "POST", makeJson(), "Authorization", str);
        return ret;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/emails";

        return url;
    }

    private String makeJson() {
        String str = "{\"token\":\"" + token;
        str += "\"}";
        return str;
    }
}
