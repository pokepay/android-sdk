package jp.pokepay.pokepaylib.BankAPI.Bill;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Bill;
import jp.pokepay.pokepaylib.SendRequest;

public class CreateBill {
    public double amount;
    public String description;
    public String accountId;

    private Constants constants = new Constants();

    public CreateBill(double amount, String description, String accountId){
        this.amount = amount;
        this.description = description;
        this.accountId = accountId;
    }

    public Bill procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Bill bill = (Bill)sendRequest.proc(new Bill(), "POST", makeJson(), "Authorization", str);
        return bill;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/bills";

        return url;
    }

    private String makeJson() {
        String str = "{\"amount\":\"" + (int)amount;
        if(description != null) {
            str += "\", \"description\":\"" + description;
        }
        if(accountId != null) {
            str += "\", \"account_id\":\"" + accountId;
        }
        str += "\"}";
        return str;
    }
}
