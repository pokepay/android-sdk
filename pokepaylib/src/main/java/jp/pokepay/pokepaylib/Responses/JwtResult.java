package jp.pokepay.pokepaylib.Responses;

import android.util.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;

import java.nio.charset.StandardCharsets;

import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Response;

public class JwtResult extends Response {
    public String data;
    public String error;

    private String decodeJWTBody(String data) throws ProcessingError {
        final String[] parts = data.split("\\.");
        if (parts.length > 1) {
            final String payload = parts[1];
            final byte[] decoded = Base64.decode(payload, Base64.URL_SAFE | Base64.NO_PADDING | Base64.NO_WRAP);
            return new String(decoded, StandardCharsets.UTF_8);
        }
        throw new ProcessingError("invalid jwt token");
    }

    public UserTransaction parseAsUserTransaction() throws ProcessingError {
        if (data != null) {
            String body = decodeJWTBody(data);
            try {
                final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return mapper.readValue(body, UserTransaction.class);
            } catch (Exception e) {
                throw new ProcessingError("Failed data map " + e.getMessage());
            }
        }
        throw new ProcessingError("Data is not set");
    }

    public BankError parseAsAPIError() throws ProcessingError {
        if (error != null) {
            String body = decodeJWTBody(error);
            try {
                final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                return mapper.readValue(body, BankError.class);
            } catch (Exception e) {
                throw new ProcessingError("Failed data map " + e.getMessage());
            }
        }
        throw new ProcessingError("Error is not set");
    }
}
