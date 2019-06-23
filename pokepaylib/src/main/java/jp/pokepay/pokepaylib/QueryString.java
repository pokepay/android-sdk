package jp.pokepay.pokepaylib;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryString {

    public static String build(String pairs[][]) {
        String query = "";
        for (int i = 0; i < pairs.length; i++) {
            String pair[] = pairs[i];
            String key = pair[0];
            String value = pair[1];
            if (key == null || value == null) continue;
            try {
                query += URLEncoder.encode(key, "UTF-8");
                query += "=";
                query += URLEncoder.encode(value, "UTF-8");
                query += "&";
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException("Broken VM does not support UTF-8");
            }
        }
        if (query.length() > 0) {
            query = "?" + query.substring(0, query.length() - 1);
        }
        return query;
    }

}