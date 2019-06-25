package jp.pokepay.pokepaylib.BankAPI;
import jp.pokepay.pokepaylib.Responses.BankError;

public class BankRequestError extends Exception {
    public BankError error;
    public BankRequestError (BankError error) {
        this.error = error;
    }
    @Override
    public String toString() {
        return this.error.toString();
    }
}

