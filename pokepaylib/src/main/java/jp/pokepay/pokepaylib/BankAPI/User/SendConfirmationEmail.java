package jp.pokepay.pokepaylib.BankAPI.User;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.SendRequest;

public class SendConfirmationEmail {
    public String id;
    public String email;

    private Constants constants = new Constants();

    public SendConfirmationEmail(String id, String email){
        this.id   = id;
        this.email = email;
    }

    public String procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        String ret = (String) sendRequest.proc(new String(), "PUT", null, "Authorization", str);
        return ret;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/users/" + id + "/emails/" + email;

        return url;
    }
}
