package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import jp.pokepay.pokepaylib.Response;

public class MessageUnreadCount extends Response {
    @JsonProperty("count")
    int count;
}
