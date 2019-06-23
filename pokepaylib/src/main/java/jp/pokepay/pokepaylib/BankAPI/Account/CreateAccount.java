package jp.pokepay.pokepaylib.BankAPI.Account;

import jp.pokepay.pokepaylib.Responses.Account;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class CreateAccount extends BankRequest {
    public String name;
    public String privateMoneyId;

    public CreateAccount(String name, String privateMoneyId) {
        this.name = name;
        this.privateMoneyId = privateMoneyId;
    }

    protected final String path() {
        return "/accounts";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    protected final String body() {
        boolean flag = false;
        String str = "{";
        if(name != null){
            str += "\"name\":\"" + name;
            flag = true;
        }
        if(privateMoneyId != null) {
            str += "\", \"private_money_id\":\"" + privateMoneyId;
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

    public final Account send(String accessToken) {
        return super.send(Account.class, accessToken);
    }
}
