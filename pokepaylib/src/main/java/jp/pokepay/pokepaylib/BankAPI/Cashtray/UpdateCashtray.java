package jp.pokepay.pokepaylib.BankAPI.Cashtray;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Cashtray;
import jp.pokepay.pokepaylib.SendRequest;

public class UpdateCashtray {
    public String id;
    public double amount;
    public String description;
    public int    expiresIn;

    private Constants constants = new Constants();

    public UpdateCashtray(String id, double amount, String description, int expiresIn){
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.expiresIn = expiresIn;
    }


    public Cashtray procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Cashtray cashtray = (Cashtray) sendRequest.proc(new Cashtray(), "PATCH", makeJson(), "Authorization", str);
        return cashtray;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/cashtrays/" + id;

        return url;
    }

    private String makeJson() {
        boolean flag = false;
        String str = "{";
        if(amount >= 0) {
            str += "\"amount\":\"" + (int) amount;
            flag = true;
        }
        if(description != null) {
            str += "\", \"description\":\"" + description;
            flag = true;
        }
        if(expiresIn >= 0){
            str += "\", \"expires_in\":\"" + expiresIn;
            flag = true;
        }

        if(flag) {
            str += "\"}";
        }
        else{
            str += "}";
        }
        return str;
    }
}
