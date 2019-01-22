package jp.pokepay.pokepaylib.MessagingAPI;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Message;
import jp.pokepay.pokepaylib.SendRequest;

public class GetMessage {
    public String id;

    private Constants constants = new Constants();

    public GetMessage(String id){
        this.id = id;
    }

    public Message procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Message message = (Message) sendRequest.proc(new Message(), "GET", null, "Authorization", str);
        return message;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/messages/" + id;

        return url;
    }
}
