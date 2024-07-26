package jp.pokepay.pokepaylib;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.json.JSONObject;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Map;

public class JsonConverter {

    public static ObjectMapper createObjectMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static String toString(final Map<String, Object> map) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }

    public static <T> T toObject(final String jsonString, final Class<T> cls)
            throws JsonParseException, JsonMappingException, IOException {
        T object = null;
        if (jsonString == null) {
            throw new InvalidParameterException("jsonString is null.");
        }
        object = createObjectMapper().readValue(jsonString, cls);
        return object;
    }

    public static <T> T toObject(String jsonString, TypeReference<T> valueTypeRef)
            throws JsonParseException, JsonMappingException, IOException {
        T object = null;
        if (jsonString == null) {
            throw new InvalidParameterException("jsonString is null.");
        }
        object = createObjectMapper().readValue(jsonString, valueTypeRef);
        return object;
    }
}
