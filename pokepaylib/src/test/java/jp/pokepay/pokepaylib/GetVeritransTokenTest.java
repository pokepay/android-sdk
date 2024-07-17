package jp.pokepay.pokepaylib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import jp.pokepay.pokepaylib.PartnerAPI.PartnerRequestError;
import jp.pokepay.pokepaylib.PartnerAPI.Veritrans.GetVeritransToken;
import jp.pokepay.pokepaylib.PartnerAPI.Veritrans.VeritransRequestError;

public class GetVeritransTokenTest {
    @Test
    public void MDKTokenCanGet() throws ProcessingError {
        try {
            new GetVeritransToken("4111111111111111", "12/23", "123", "cd76ca65-7f54-4dec-8ba3-11c12e36a548").send();
        } catch (PartnerRequestError e) {
            fail(e.toString());
        }
    }

    @Test
    public void InvalidExpireDate() throws ProcessingError, PartnerRequestError {
        try {
            new GetVeritransToken("4111111111111111", "1223", "123", "cd76ca65-7f54-4dec-8ba3-11c12e36a548").send();
            fail();
        } catch (VeritransRequestError e) {
            assertEquals("failure", e.error.status);
            assertEquals("invalid_card_expire", e.error.code);
            assertEquals("Card expire is invalid.", e.error.message);
        }
    }
}
