package jp.pokepay.pokepaylib.BankAPI.Bill;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Bill;

public class GetBill extends BankRequest {
    @NonNull
    public String id;

    public GetBill(@NonNull String id) {
        this.id = id;
    }

    protected final String path() {
        return "/bills/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final Bill send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(Bill.class, accessToken);
    }
}
