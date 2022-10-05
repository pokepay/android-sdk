package jp.pokepay.pokepaylib.BankAPI.Account;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.SevenElevenAtmSession;

public class GetAccountSevenElevenAtmSession extends BankRequest {
    @NonNull
    public String qrInfo;

    public GetAccountSevenElevenAtmSession(@NonNull String qrInfo) {
        this.qrInfo = qrInfo;
    }

    protected final String path() {
        return "/seven-bank-atm-sessions/" + qrInfo;
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final SevenElevenAtmSession send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(SevenElevenAtmSession.class, accessToken);
    }
}
