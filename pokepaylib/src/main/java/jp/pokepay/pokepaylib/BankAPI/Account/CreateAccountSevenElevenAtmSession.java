package jp.pokepay.pokepaylib.BankAPI.Account;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.SevenElevenAtmSession;

public class CreateAccountSevenElevenAtmSession extends BankRequest {

    @NonNull
    public String accountId;
    @NonNull
    public String qrInfo;
    @NonNull
    public double amount;

    public CreateAccountSevenElevenAtmSession(String accountId, String qrInfo, double amount) {
        this.accountId = accountId;
        this.qrInfo = qrInfo;
        this.amount = amount;
    }

    protected final String path() {
        return "/accounts/" + accountId + "/seven-bank-atm-sessions";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("qr_info", qrInfo);
            put("amount", amount);
        }};
    }

    public final SevenElevenAtmSession send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(SevenElevenAtmSession.class, accessToken);
    }

}
