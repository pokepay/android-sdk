package jp.pokepay.pokepaylib.Responses;

import jp.pokepay.pokepaylib.Response;

public class BankError extends Response {
    public String type;
    public String message;
    public Object errors;
}
