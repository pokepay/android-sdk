package jp.pokepay.pokepaylib.Parameters;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Map;

import jp.pokepay.pokepaylib.Parameter;
@JsonSerialize
public class Metadata extends Parameter {
    public Map<String, String> map;

    public Metadata(Map<String, String> map){
        this.map = map;
    }
}
