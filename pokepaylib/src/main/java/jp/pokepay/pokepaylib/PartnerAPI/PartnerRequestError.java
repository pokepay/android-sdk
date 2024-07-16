package jp.pokepay.pokepaylib.PartnerAPI;

import jp.pokepay.pokepaylib.Responses.PartnerError;

public class PartnerRequestError extends Exception {
    public int statusCode;
    public PartnerError error;

    public PartnerRequestError(int statusCode, PartnerError error) {
        this.statusCode = statusCode;
        this.error = error;
    }

    @Override
    public String toString() {
        return this.error.toString();
    }
}
