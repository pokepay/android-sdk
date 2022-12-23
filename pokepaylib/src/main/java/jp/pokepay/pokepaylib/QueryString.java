package jp.pokepay.pokepaylib;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class QueryString {

    public static String build(String[][] pairs) {
        StringBuilder query = new StringBuilder();
        for (String[] pair : pairs) {
            String key = pair[0];
            String value = pair[1];
            if (key == null || value == null) continue;
            try {
                query.append(URLEncoder.encode(key, "UTF-8"));
                query.append("=");
                query.append(URLEncoder.encode(value, "UTF-8"));
                query.append("&");
            } catch (UnsupportedEncodingException ex) {
                throw new RuntimeException("Broken VM does not support UTF-8");
            }
        }
        if (query.length() > 0) {
            query = new StringBuilder("?" + query.substring(0, query.length() - 1));
        }
        return query.toString();
    }

}