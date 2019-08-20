package jp.pokepay.pokepaylib;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parameter {

    public Map<String, Object> toMap() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> rt = mapper.convertValue(this, Map.class);
        return rt;
    }

}
