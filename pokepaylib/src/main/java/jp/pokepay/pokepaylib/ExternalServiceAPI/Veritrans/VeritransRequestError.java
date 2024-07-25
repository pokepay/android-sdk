package jp.pokepay.pokepaylib.ExternalServiceAPI.Veritrans;

import jp.pokepay.pokepaylib.ExternalServiceAPI.ExternalServiceRequestError;

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
