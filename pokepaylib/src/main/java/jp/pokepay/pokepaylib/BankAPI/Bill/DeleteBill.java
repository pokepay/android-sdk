package jp.pokepay.pokepaylib.BankAPI.Bill;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.SendRequest;

public class DeleteBill {
    public String id;

    private Constants constants = new Constants();

    public DeleteBill(String id){
        this.id = id;
    }

    public String procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        String ret = (String)sendRequest.proc(new String(), "DELETE", null, "Authorization", str);
        return ret;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/bills/" + id;

        return url;
    }
}
