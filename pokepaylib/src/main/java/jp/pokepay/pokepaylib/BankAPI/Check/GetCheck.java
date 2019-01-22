package jp.pokepay.pokepaylib.BankAPI.Check;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.SendRequest;

public class GetCheck {
    public String id;

    private Constants constants = new Constants();

    public GetCheck(String id){
        this.id = id;
    }

    public Check procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Check check = (Check)sendRequest.proc(new Check(), "GET", null, "Authorization", str);
        return check;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/checks/" + id;

        return url;
    }
}
