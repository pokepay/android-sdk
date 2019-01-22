package jp.pokepay.pokepaylib.BankAPI.Terminal;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.ServerKey;
import jp.pokepay.pokepaylib.SendRequest;

public class AddTerminalPublicKey {
    public String key;

    private Constants constants = new Constants();

    public AddTerminalPublicKey(String key){
        this.key = key;
    }

    public ServerKey procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        ServerKey serverKey =  (ServerKey)sendRequest.proc(new ServerKey(), "POST", makeJson(), "Authorization", str);
        return serverKey;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/terminal/keys";

        return url;
    }

    private String makeJson(){
        return "{\"key\":\"" + key + "\"}";
    }
}
