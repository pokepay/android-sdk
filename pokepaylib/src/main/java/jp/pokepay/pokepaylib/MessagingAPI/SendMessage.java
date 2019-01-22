package jp.pokepay.pokepaylib.MessagingAPI;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Message;
import jp.pokepay.pokepaylib.SendRequest;

public class SendMessage {
    public String toUserId;
    public double amount;
    public String subject;
    public String body;
    public String fromAccountId;
    public String requestId;

    private Constants constants = new Constants();

    public SendMessage(String toUserId, double amount, String subject, String body, String fromAccountId, String requestId){
        this.toUserId = toUserId;
        this.amount = amount;
        this.subject = subject;
        this.body = body;
        this.fromAccountId = fromAccountId;
        this.requestId = requestId;
    }

    public Message procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Message message = (Message)sendRequest.proc(new Message(), "POST", makeJson(), "Authorization", str);
        return message;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/messages";

        return url;
    }

    private String makeJson() {
        String str = "{\"to_user_id\":\"" + toUserId;
        if(amount > -1){
            str += "\", \"amount\":\"" + amount;
        }
        str += "\", \"subject\":\"" + subject;
        str += "\", \"body\":\"" + body;
        if(fromAccountId != null) {
            str += "\", \"from_account_id\":\"" + fromAccountId;
        }
        if(requestId != null) {
            str += "\", \"_request_id\":\"" + requestId;
        }
        str += "\"}";
        return str;
    }
}
