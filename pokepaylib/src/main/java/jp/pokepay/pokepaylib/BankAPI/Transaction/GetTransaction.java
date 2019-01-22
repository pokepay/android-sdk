package jp.pokepay.pokepaylib.BankAPI.Transaction;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.SendRequest;

public class GetTransaction {
    public String id;

    private Constants constants = new Constants();

    public GetTransaction(String id){
        this.id = id;
    }

    public UserTransaction procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        UserTransaction userTransaction = (UserTransaction)sendRequest.proc(new UserTransaction(), "GET", null, "Authorization", str);
        return userTransaction;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/transactions/" + id;

        return url;
    }

}
