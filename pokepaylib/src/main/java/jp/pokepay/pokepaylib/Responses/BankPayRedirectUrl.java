package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class BankPayRedirectUrl extends Response {
    @NonNull
    public String redirectUrl;
    public String paytreeCustomerNumber;
}
