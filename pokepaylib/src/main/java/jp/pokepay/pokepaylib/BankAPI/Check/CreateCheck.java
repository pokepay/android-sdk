package jp.pokepay.pokepaylib.BankAPI.Check;


import jp.pokepay.pokepaylib.Constants;
import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.SendRequest;

public class CreateCheck {
    public double amount;
    public String description;
    public String accountId;

    private Constants constants = new Constants();

    public CreateCheck(double amount, String description, String accountId){
        this.amount = amount;
        this.description = description;
        this.accountId = accountId;
    }

    public Check procSend(String accessToken){
        String url = makeURL();
        SendRequest sendRequest = new SendRequest(url);
        String str = constants.AUTHORIZATION + accessToken;
        Check check = (Check)sendRequest.proc(new Check(), "POST", makeJson(), "Authorization", str);
        return check;
    }

    private String makeURL(){
        String url = constants.API_BASE_URL + "/checks";

        return url;
    }

    private String makeJson() {
        boolean flag = false;
        String str = "{";
        if(amount != -1) {
            str += "\"amount\":\"" + amount;
            flag = true;
        }
        if(description != null) {
            str += "\", \"description\":\"" + description;
            flag = true;
        }
        if(accountId != null) {
            str += "\", \"account_id\":\"" + accountId;
            flag = true;
        }
        if(flag) {
            str += "\"}";
        }
        else{
            str += "}";
        }
        return str;
    }
}
