package jp.pokepay.pokepaylib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.BankAPI.autogen.requests.*;
import jp.pokepay.pokepaylib.PartnerAPI.Veritrans.GetVeritransToken;
import jp.pokepay.pokepaylib.BankAPI.autogen.responses.*;
import jp.pokepay.pokepaylib.Responses.VeritransToken;

public class CreateCreditCardTest {
    final String userId = "a0bd7408-5087-4459-9e74-a21d7acc18d4";
    final String accessToken = "55nF6HJRyGCHgvEQZP7Pd5K33ggX-C5ZH_q3eqqe5Cg1dGeV_G2-CZ0rmChJ1OcN";

    private String fetchVeritransMdkToken() {
        final String testCardNumber = "4111111111111111";
        final String testTokenApiKey = "9ad14759-7082-457e-a874-864c78edc05a";

        try {
            VeritransToken response = new GetVeritransToken(testCardNumber, "12/23", "123", testTokenApiKey).send();
            return response.token;
        } catch (Exception e) {
            fail();
            return "";
        }
    }

    @Test
    public void canCreate() throws BankRequestError, ProcessingError {
        final String mdkToken = fetchVeritransMdkToken();

        CreditCard response = new CreateCreditCard(userId, mdkToken, "coilinc").send(accessToken);
        assertEquals("411111********11", response.card_number);
    }

    @Test
    public void invalidMDKToken() throws ProcessingError {
        final String mdkToken = "INVALID";
        try {
            CreditCard response = new CreateCreditCard(userId, mdkToken, "coilinc").send(accessToken);
            fail();
        }catch (BankRequestError e) {
            assertEquals("{\n" +
                    "  \"type\" : \"invalid_mdk_token\",\n" +
                    "  \"message\" : \"Invalid MDK token (OC09000000000000)\",\n" +
                    "  \"errors\" : null\n" +
                    "}", e.toString());
        }
    }
}
