package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class JwtResult extends Response {
    public String data;
    public String error;
    // TODO: add some parse methods.
}
