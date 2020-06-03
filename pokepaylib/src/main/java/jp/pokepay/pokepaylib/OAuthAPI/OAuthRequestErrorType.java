package jp.pokepay.pokepaylib.OAuthAPI;

public enum OAuthRequestErrorType {
    unknown,
    invalidRequest,
    invalidClient,
    invalidGrant,
    unauthorizedClient,
    unsupportedGrantType,
    invalidScope,
    unrecognizedClient,
    invalidRedirectUri,
    unsupportedResponseType,
}
