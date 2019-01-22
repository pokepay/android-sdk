package jp.pokepay.pokepaylib.BankAPI.Transaction;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.SendRequest;

public class CreateTransactionWithCheck {
    public String checkId;
    public String accountId;

    private Constants constants = new Constants();

    public CreateTransactionWithCheck(String checkId, String accountId){
        this.checkId   = checkId;
        this.accountId = accountId;
    }

    public UserTransaction procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        UserTransaction userTransaction = (UserTransaction)sendRequest.proc(new UserTransaction(), "POST", makeJson(), "Authorization", str);
        return userTransaction;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/transactions";

        return url;
    }

    private String makeJson() {
        String str = "{\"check_id\":\"" + checkId;
        if(accountId != null) {
            str += "\", \"account_id\":\"" + accountId;
        }
        str += "\"}";
        return str;
    }
}