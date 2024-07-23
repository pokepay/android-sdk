package jp.pokepay.pokepaylib;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.BankAPI.autogen.requests.GetCreditCards;

public class GetCreditCardsTest {
    final String userId = "a0bd7408-5087-4459-9e74-a21d7acc18d4";
    final String accessToken = "55nF6HJRyGCHgvEQZP7Pd5K33ggX-C5ZH_q3eqqe5Cg1dGeV_G2-CZ0rmChJ1OcN";

    @Test
    public void canGetCards() throws BankRequestError, ProcessingError {
        new GetCreditCards(userId, "coilinc").send(accessToken);
        new GetCreditCards(userId, "coilinc").perPage(10).send(accessToken);
    }

    @Test
    public void veritransMerchantNotSet() throws ProcessingError {
        try {
            new GetCreditCards(userId, "hoge").send(accessToken);
        } catch (BankRequestError e) {
            assertEquals("{\n" +
                    "  \"type\" : \"veritrans_merchant_not_set\",\n" +
                    "  \"message\" : \"Credit cards are not accepted at this money\",\n" +
                    "  \"errors\" : null\n" +
                    "}", e.toString());
        }
    }
}
