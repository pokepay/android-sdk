package jp.pokepay.pokepaylib.PartnerAPI.Veritrans;

import jp.pokepay.pokepaylib.PartnerAPI.PartnerRequestError;

public class VeritransRequestError extends PartnerRequestError {
    public VeritransError error;

    public VeritransRequestError(VeritransError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error.toString();
    }
}
