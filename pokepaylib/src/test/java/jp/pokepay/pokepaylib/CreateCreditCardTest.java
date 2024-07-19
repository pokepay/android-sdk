package jp.pokepay.pokepaylib;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.BankAPI.User.CreateCreditCard;
import jp.pokepay.pokepaylib.PartnerAPI.Veritrans.GetVeritransToken;
import jp.pokepay.pokepaylib.Responses.CreditCard;
import jp.pokepay.pokepaylib.Responses.VeritransToken;

public class CreateCreditCardTest {
    final String userId = "a0bd7408-5087-4459-9e74-a21d7acc18d4";
    final String accessToken = "55nF6HJRyGCHgvEQZP7Pd5K33ggX-C5ZH_q3eqqe5Cg1dGeV_G2-CZ0rmChJ1OcN";

    private String fetchVeritransMdkToken() {
        final String testCardNumber = "4111111111111111";
        final String testTokenApiKey = "cd76ca65-7f54-4dec-8ba3-11c12e36a548";

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

        CreditCard response = new CreateCreditCard(userId, mdkToken).send(accessToken);
        assertEquals("411111********11", response.card_number);
    }

    @Test
    public void invalidMDKToken() throws ProcessingError {
        final String mdkToken = "INVALID";
        try {
            CreditCard response = new CreateCreditCard(userId, mdkToken).send(accessToken);
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
