package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import jp.pokepay.pokepaylib.Response;

public class Images extends Response {
    public String card;

    @JsonProperty("300x300")
    public String res300;

    @JsonProperty("600x600")
    public String res600;
}
