package jp.pokepay.pokepaylib.Parameters;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import jp.pokepay.pokepaylib.Parameter;
@JsonSerialize
public class Metadata extends Parameter {
    public String key1;

    public Metadata(String key1){
        this.key1 = key1;
    }
}
