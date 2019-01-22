package jp.pokepay.pokepaylib.MessagingAPI;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.MessageUnreadCount;
import jp.pokepay.pokepaylib.SendRequest;

public class GetUnreadCount {
    private Constants constants = new Constants();

    public GetUnreadCount(){
    }

    public MessageUnreadCount procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        MessageUnreadCount messageUnreadCount = (MessageUnreadCount) sendRequest.proc(new MessageUnreadCount(), "GET", null, "Authorization", str);
        return messageUnreadCount;
    }


    private String makeURL(){
        String url = constants.API_BASE_URL + "/messages/unread-count";

        return url;
    }
}


