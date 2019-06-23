package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class NoContent {

    public NoContent() {}

    public void print(){
        System.out.println("NoContent()");
    }
}
