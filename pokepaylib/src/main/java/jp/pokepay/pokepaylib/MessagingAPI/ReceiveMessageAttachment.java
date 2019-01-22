package jp.pokepay.pokepaylib.MessagingAPI;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Message;
import jp.pokepay.pokepaylib.Responses.MessageAttachment;
import jp.pokepay.pokepaylib.SendRequest;

public class ReceiveMessageAttachment {
    public Message message;

    private Constants constants = new Constants();

    public ReceiveMessageAttachment(Message message){
        this.message = message;
    }

    public MessageAttachment procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        MessageAttachment messageAttachment = (MessageAttachment) sendRequest.proc(new MessageAttachment(), "POST", null, "Authorization", str);
        return messageAttachment;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/messages/" + message.id + "/attachment/receive";

        return url;
    }

}
