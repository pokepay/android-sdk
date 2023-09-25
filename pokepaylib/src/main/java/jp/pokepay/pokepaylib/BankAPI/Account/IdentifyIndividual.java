package jp.pokepay.pokepaylib.BankAPI.Account;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.Parameters.Gender;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.IdentificationResult;

public class IdentifyIndividual extends BankRequest {
    @NonNull
    public String accountId;
    @NonNull
    public String signature;
    @NonNull
    public String signingCert;
    @NonNull
    public String expectedHash;
    public String name;
    public Gender gender;
    public String address;
    /**
     * pattern = YYYY-MM-DD
     */
    public String dateOfBirth;

    public IdentifyIndividual(@NonNull String accountId, @NonNull String signature, @NonNull String signingCert, @NonNull String expectedHash, String name, Gender gender, String address, String dateOfBirth) {
        this.accountId = accountId;
        this.signature = signature;
        this.signingCert = signingCert;
        this.expectedHash = expectedHash;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    protected final String path() {
        return "/accounts/" + accountId + "/individual-numbers/identification";
    }

    protected final Request.Method method() {
        return Request.Method.POST;
    }

    @Override
    protected final Map<String, Object> parameters() {
        return new HashMap<String, Object>() {{
            put("signature", signature);
            put("signing_cert", signingCert);
            put("expected_hash", expectedHash);

            if (name != null) {
                put("name", name);
            }

            if (gender != null) {
                put("gender", gender.getGender());
            }

            if (address != null) {
                put("address", address);
            }

            if (dateOfBirth != null) {
                put("date_of_birth", dateOfBirth);
            }
        }};
    }

    public final IdentificationResult send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(IdentificationResult.class, accessToken);
    }
}