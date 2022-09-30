package jp.pokepay.pokepaylib.BankAPI.User;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.UserSettingUrl;

public class GetUserSettingUrl extends BankRequest {

    protected final String path() { return "/oauth/full-access"; }

    protected final Request.Method method() { return Request.Method.POST; }

    public final UserSettingUrl send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(UserSettingUrl.class, accessToken);
    }

}
