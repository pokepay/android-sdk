package jp.pokepay.pokepaylib.BankAPI.PrivateMoney;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.PrivateMoney;

/**
 * @deprecated Use {@link jp.pokepay.pokepaylib.BankAPI.autogen.requests.GetPrivateMoney} instead.
 *     This hand-written request is being replaced by the auto-generated API.
 */
@Deprecated
public class GetPrivateMoney extends BankRequest {
    @NonNull
    public String privateMoneyId;

    public GetPrivateMoney(@NonNull String privateMoneyId) {
        this.privateMoneyId = privateMoneyId;
    }

    @Override
    protected String path() {
        return "/private-moneys/" + privateMoneyId;
    }

    @Override
    protected Request.Method method() { return Request.Method.GET; }

    public final PrivateMoney send(String accessToken)  throws ProcessingError, BankRequestError {
        return super.send(PrivateMoney.class, accessToken);
    }
}
