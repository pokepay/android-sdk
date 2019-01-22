package jp.pokepay.pokepaylib.BankAPI.Transaction;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.SendRequest;

public class SendToUser {
    public String userId;
    public double amount;
    public String receiverTerminalId;
    public String senderAccountId;
    public String description;

    private Constants constants = new Constants();

    public SendToUser(String userId, double amount, String receiverTerminalId, String senderAccountId, String description){
        this.userId = userId;
        this.amount = amount;
        this.receiverTerminalId = receiverTerminalId;
        this.senderAccountId = senderAccountId;
        this.description = description;
    }

    public UserTransaction procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        UserTransaction userTransaction = (UserTransaction)sendRequest.proc(new UserTransaction(), "POST", makeJson(), "Authorization", str);
        return userTransaction;
    }


    private String makeURL(){
        String url = constants.API_BASE_URL + "/users/" + userId + "/transactions";

        return url;
    }

    private String makeJson() {
        String str = "{\"amount\":\"" + amount;
        if(receiverTerminalId != null) {
            str += "\", \"receiver_terminal_id\":\"" + receiverTerminalId;
        }
        if(senderAccountId != null){
            str += "\", \"sender_account_id\":\"" + senderAccountId;
        }
        if(description != null){
            str += "\", \"description\":\"" + description;
        }
        str += "\"}";
        return str;
    }
}
