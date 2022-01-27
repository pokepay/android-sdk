package jp.pokepay.pokepaylib;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Parameter {

    public Map<String, Object> toMap() {
        final ObjectMapper mapper = JsonConverter.createObjectMapper();
        return mapper.convertValue(this, Map.class);
    }

}
