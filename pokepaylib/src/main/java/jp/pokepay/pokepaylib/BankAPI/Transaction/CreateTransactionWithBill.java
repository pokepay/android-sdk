package jp.pokepay.pokepaylib.BankAPI.Transaction;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.SendRequest;

public class CreateTransactionWithBill {
    public String billId;
    public String accountId;
    public double amount;

    Constants constants = new Constants();

    public CreateTransactionWithBill(String billId, String accountId, double amount){
        this.billId    = billId;
        this.accountId = accountId;
        this.amount    = amount;
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
        String str = "{\"bill_id\":\"" + billId;
        if(accountId != null) {
            str += "\", \"account_id\":\"" + accountId;
        }
        if(amount >= 0) {
            str += "\", \"amount\":\"" + amount;
        }
        str += "\"}";
        return str;
    }

}
