package jp.pokepay.pokepaylib.BankAPI.User;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.User;
import jp.pokepay.pokepaylib.SendRequest;

public class UpdateUser {
    public String id;
    public String name;

    private Constants constants = new Constants();

    public UpdateUser(String id, String name){
        this.id   = id;
        this.name = name;
    }

    public User procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        User user = (User) sendRequest.proc(new User(), "PATCH", makeJson(), "Authorization", str);
        return user;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/users/" + id;

        return url;
    }

    private String makeJson() {
        if(name == null){
            return "{}";
        }
        return "{\"name\":\"" + name + "\"}";
    }
}
