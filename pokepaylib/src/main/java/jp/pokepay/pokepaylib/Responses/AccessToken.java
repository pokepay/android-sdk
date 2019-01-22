package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class AccessToken {
    public String access_token;
    public String refresh_token;
    public String token_type;
    public int    expires_in;

    public AccessToken(){

    }

    public void print(){
        System.out.println("AccessToken(");
        System.out.println("accessToken: \"" + access_token + "\",");
        System.out.println("refreshToken: \"" + refresh_token + "\",");
        System.out.println("tokenType: \"" + token_type + "\",");
        System.out.println("expiresIn: \"" + expires_in + "\",");
        System.out.println("),");
    }
}
