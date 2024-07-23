package jp.pokepay.pokepaylib.PartnerAPI.Veritrans;

import jp.pokepay.pokepaylib.PartnerAPI.ExternalServiceRequestError;

public class VeritransRequestError extends ExternalServiceRequestError {
    public VeritransError error;

    public VeritransRequestError(VeritransError error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error.toString();
    }
}
