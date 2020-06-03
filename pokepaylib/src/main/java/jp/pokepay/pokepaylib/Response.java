package jp.pokepay.pokepaylib;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class Response {

    final static SimpleDateFormat formatter;

    static {
        formatter =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    public String toString() {
        try {
            final ObjectMapper m = JsonConverter.createObjectMapper();
            m.setDateFormat(formatter);
            return m.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
