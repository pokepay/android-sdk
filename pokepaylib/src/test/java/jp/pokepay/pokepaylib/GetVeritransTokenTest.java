package jp.pokepay.pokepaylib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import jp.pokepay.pokepaylib.PartnerAPI.PartnerRequestError;
import jp.pokepay.pokepaylib.PartnerAPI.Veritrans.GetVeritransToken;
import jp.pokepay.pokepaylib.PartnerAPI.Veritrans.VeritransRequestError;

public class GetVeritransTokenTest {
    private final String testCardNumber = "4111111111111111";
    private final String testTokenApiKey = "cd76ca65-7f54-4dec-8ba3-11c12e36a548";

    @Test
    public void MDKTokenCanGet() throws ProcessingError {
        try {
            new GetVeritransToken(testCardNumber, "12/23", "123", testTokenApiKey).send();
        } catch (PartnerRequestError e) {
            fail(e.toString());
        }
    }

    @Test
    public void ThrowErrorIfdExpireDateIsInvalid() throws ProcessingError, PartnerRequestError {
        try {
            new GetVeritransToken(testCardNumber, "1223", "123", testTokenApiKey).send();
            fail();
        } catch (VeritransRequestError e) {
            assertEquals("failure", e.error.status);
            assertEquals("invalid_card_expire", e.error.code);
            assertEquals("Card expire is invalid.", e.error.message);
        }
    }
}
