package jp.pokepay.pokepaylib.BankAPI.Check;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.SendRequest;

public class UpdateCheck {
    public String id;
    public double amount;
    public String description;

    private Constants constants = new Constants();

    public UpdateCheck(String id, double amount, String description){
        this.id = id;
        this.amount = amount;
        this.description = description;
    }


    public Check procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Check check = (Check)sendRequest.proc(new Check(), "PATCH", makeJson(), "Authorization", str);
        return check;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/checks/" + id;

        return url;
    }

    private String makeJson() {
        boolean flag = false;
        String str = "{";
        if(amount > 0) {
            str += "\"amount\":\"" + (int) amount;
            flag = true;
        }
        if(description != null) {
            str += "\", \"description\":\"" + description;
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
