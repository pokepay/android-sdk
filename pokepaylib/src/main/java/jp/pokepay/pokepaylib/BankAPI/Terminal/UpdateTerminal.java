package jp.pokepay.pokepaylib.BankAPI.Terminal;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Terminal;
import jp.pokepay.pokepaylib.SendRequest;

public class UpdateTerminal {
    public String accountId;
    public String name;
    public String pushToken;

    private Constants constants = new Constants();

    public UpdateTerminal(String accountId, String name, String pushToken){
        this.accountId = accountId;
        this.name      = name;
        this.pushToken = pushToken;
    }

    public Terminal procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Terminal terminal = (Terminal) sendRequest.proc(new Terminal(), "PATCH", makeJson(), "Authorization", str);
        return terminal;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/terminal";

        return url;
    }

    private String makeJson() {
        String str = "{\"account_id\":\"" + accountId;
        str += "\", \"name\":\"" + name;
        str += "\", \"push_token\":\"" + pushToken;
        str += "\"}";
        return str;
    }
}
