package jp.pokepay.pokepaylib.OAuthAPI;
import jp.pokepay.pokepaylib.Responses.OAuthError;

public class OAuthRequestError extends Exception {
    public OAuthError error;
    public OAuthRequestError (OAuthError error) {
        this.error = error;
    }
    @Override
    public String toString() {
        return this.error.toString();
    }
}