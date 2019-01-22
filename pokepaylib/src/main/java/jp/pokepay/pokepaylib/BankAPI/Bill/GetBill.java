package jp.pokepay.pokepaylib.BankAPI.Bill;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Bill;
import jp.pokepay.pokepaylib.SendRequest;

public class GetBill {
    public String id;

    private Constants constants = new Constants();

    public GetBill(String id){
        this.id = id;
    }

    public Bill procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Bill bill = (Bill)sendRequest.proc(new Bill(), "GET", null, "Authorization", str);
        return bill;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/bills/" + id;

        return url;
    }
}
