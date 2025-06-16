package jp.pokepay.pokepaylib.BankAPI;
import jp.pokepay.pokepaylib.Responses.BankError;

public class BankRequestError extends Exception {
    public int statusCode;
    public BankError error;
    public BankRequestError (int statusCode, BankError error) {
        super("statusCode=" + statusCode + ", error=" + error);
        this.statusCode = statusCode;
        this.error = error;
    }
    @Override
    public String toString() {
        return this.error.toString();
    }
}

