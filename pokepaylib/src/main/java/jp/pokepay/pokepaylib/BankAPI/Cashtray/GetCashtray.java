package jp.pokepay.pokepaylib.BankAPI.Cashtray;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Cashtray;
import jp.pokepay.pokepaylib.SendRequest;

public class GetCashtray {
    public String id;

    private Constants constants = new Constants();

    public GetCashtray(String id){
        this.id = id;
    }

    public Cashtray procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Cashtray cashtray = (Cashtray)sendRequest.proc(new Cashtray(), "GET", null, "Authorization", str);
        return cashtray;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/cashtrays/" + id;

        return url;
    }
}
