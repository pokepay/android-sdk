package jp.pokepay.pokepaylib.BankAPI.Cashtray;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Cashtray;
import jp.pokepay.pokepaylib.SendRequest;

public class CreateCashtray {
    public double amount;
    public String description;
    public int expiresIn;

    private Constants constants = new Constants();

    public CreateCashtray(double amount, String description, int expiresIn){
        this.amount = amount;
        this.description = description;
        this.expiresIn = expiresIn;
    }

    public Cashtray procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Cashtray cashtray = (Cashtray)sendRequest.proc(new Cashtray(), "POST", makeJson(), "Authorization", str);
        return cashtray;
    }

    // ToDo:サーバに沿ったurlの作り方を調べる
    private String makeURL(){
        String url = constants.API_BASE_URL + "/cashtrays";

        return url;
    }

    private String makeJson() {
        String str = "{\"amount\":\"" + (int)amount;
        if(description != null) {
            str += "\", \"description\":\"" + description;
        }
        if(expiresIn != -1){
            str += "\", \"expires_in\":\"" + expiresIn;
        }
        str += "\"}";
        return str;
    }
}
