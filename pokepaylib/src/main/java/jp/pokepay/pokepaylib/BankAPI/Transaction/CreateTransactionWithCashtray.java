package jp.pokepay.pokepaylib.BankAPI.Transaction;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.SendRequest;

public class CreateTransactionWithCashtray {
    public String cashtrayId;
    public String accountId;

    private Constants constants = new Constants();

    public CreateTransactionWithCashtray(String cashtrayId, String accountId) {
        this.cashtrayId = cashtrayId;
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
        String str = "{\"cashtray_id\":\"" + cashtrayId;
        if(accountId != null) {
            str += "\", \"account_id\":\"" + accountId;
        }
        str += "\"}";
        return str;
    }

}