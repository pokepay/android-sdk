package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Image {
    public String card;

    @JsonProperty("300x300")
    public String res300;

    @JsonProperty("600x600")
    public String res600;

    public void print(){
        System.out.println("Images(");
        System.out.println("card: \"" + card + "\",");
        System.out.println("300x300: \"" + res300 + "\",");
        System.out.println("600x600: \"" + res600 + "\",");
        System.out.println("),");
    }
}
