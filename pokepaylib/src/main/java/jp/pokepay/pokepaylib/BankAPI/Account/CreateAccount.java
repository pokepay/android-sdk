package jp.pokepay.pokepaylib.BankAPI.Account;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Account;
import jp.pokepay.pokepaylib.SendRequest;

public class CreateAccount {
    public String name;
    public String privateMoneyId;

    private Constants constants = new Constants();

    public CreateAccount(String name, String privateMoneyId){
        this.name = name;
        this.privateMoneyId = privateMoneyId;
    }

    public Account procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Account account = (Account)sendRequest.proc(new Account(), "POST", makeJson(), "Authorization", str);
        return account;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/accounts";

        return url;
    }

    private String makeJson() {
        boolean flag = false;
        String str = "{";
        if(name != null){
            str += "\"name\":\"" + name;
            flag = true;
        }
        if(privateMoneyId != null) {
            str += "\", \"private_money_id\":\"" + privateMoneyId;
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
