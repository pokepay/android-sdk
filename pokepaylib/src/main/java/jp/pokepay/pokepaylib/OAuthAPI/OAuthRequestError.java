package jp.pokepay.pokepaylib.OAuthAPI;
import jp.pokepay.pokepaylib.Responses.OAuthError;

public class OAuthRequestError extends Exception {
    public int statusCode;
    public OAuthError error;
    public OAuthRequestError (int statusCode, OAuthError error) {
        this.statusCode = statusCode;
        this.error = error;
    }
    @Override
    public String toString() {
        return this.error.toString();
    }
}