package jp.pokepay.pokepaylib.BankAPI.Bill;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Bill;
import jp.pokepay.pokepaylib.SendRequest;

public class UpdateBill {
    public String id;
    public double amount;
    public String description;

    private Constants constants = new Constants();

    public UpdateBill(String id, double amount, String description){
        this.id = id;
        this.amount = amount;
        this.description = description;
    }


    public Bill procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Bill bill = (Bill)sendRequest.proc(new Bill(), "PATCH", makeJson(), "Authorization", str);
        return bill;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/bills/" + id;

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

        if(flag) {
            str += "\"}";
        }
        else{
            str += "}";
        }
        return str;
    }

}
