package jp.pokepay.pokepaylib.BankAPI.Transaction;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Jwt;
import jp.pokepay.pokepaylib.SendRequest;

public class CreateTransactionWithJwt {
    public String data;
    public String accountId;

    private Constants constants = new Constants();

    public CreateTransactionWithJwt(String data, String accountId){
        this.data = data;
        this.accountId = accountId;
    }

    public String procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Jwt jwt = (Jwt)sendRequest.proc(new Jwt(), "POST", makeJson(), "Authorization", str);
        if(jwt == null){
            return null;
        }
        return jwt.data;
    }


    private String makeURL(){
        String url = constants.API_BASE_URL + "/transactions";

        return url;
    }

    private String makeJson() {
        String str = "{\"data\":\"" + data;
        if(accountId != null) {
            str += "\", \"account_id\":\"" + accountId;
        }
        str += "\"}";
        return str;
    }
}
