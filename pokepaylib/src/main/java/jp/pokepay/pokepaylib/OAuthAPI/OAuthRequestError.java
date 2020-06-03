package jp.pokepay.pokepaylib.OAuthAPI;
import jp.pokepay.pokepaylib.Responses.OAuthError;

public class OAuthRequestError extends Exception {
    public int statusCode;
    public OAuthError error;
    public OAuthRequestErrorType type;
    public OAuthRequestError (int statusCode, OAuthError error) {
        this.statusCode = statusCode;
        this.error = error;
        switch (this.error.error) {
            case "invalid_request": this.type = OAuthRequestErrorType.invalidRequest; break;
            case "invalid_client": this.type = OAuthRequestErrorType.invalidClient; break;
            case "invalid_grant": this.type = OAuthRequestErrorType.invalidGrant; break;
            case "unauthorized_client": this.type = OAuthRequestErrorType.unauthorizedClient; break;
            case "unsupported_grant_type": this.type = OAuthRequestErrorType.unsupportedGrantType; break;
            case "invalid_scope": this.type = OAuthRequestErrorType.invalidScope; break;
            case "unrecognized_client": this.type = OAuthRequestErrorType.unrecognizedClient; break;
            case "invalid_redirect_uri": this.type = OAuthRequestErrorType.invalidRedirectUri; break;
            case "unsupported_response_type": this.type = OAuthRequestErrorType.unsupportedResponseType; break;
            default: this.type = OAuthRequestErrorType.unknown; break;
        }
    }
    @Override
    public String toString() {
        return this.error.toString();
    }
}