package jp.pokepay.pokepaylib.BankAPI.Account;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Account;
import jp.pokepay.pokepaylib.SendRequest;

public class GetAccount {
    public String id;

    private Constants constants = new Constants();

    public GetAccount(String id){
        this.id = id;
    }

    public Account procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Account account = (Account)sendRequest.proc(new Account(), "GET", null, "Authorization", str);
        return account;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/accounts/" + id;

        return url;
    }
}
