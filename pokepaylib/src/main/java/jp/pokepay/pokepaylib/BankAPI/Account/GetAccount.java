package jp.pokepay.pokepaylib.BankAPI.Account;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Account;

public class GetAccount extends BankRequest {
    @NonNull
    public String id;

    public GetAccount(String id){
        this.id = id;
    }

    protected final String path() {
        return "/accounts/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final Account send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Account.class, accessToken);
    }
}
