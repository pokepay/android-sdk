package jp.pokepay.pokepaylib.BankAPI.Terminal;

import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Terminal;
import jp.pokepay.pokepaylib.SendRequest;

public class GetTerminal {
    Constants constants = new Constants();

    public GetTerminal(){
    }

    public Terminal procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Terminal terminal = (Terminal)sendRequest.proc(new Terminal(), "GET", null, "Authorization", str);
        return terminal;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/terminal";

        return url;
    }
}
