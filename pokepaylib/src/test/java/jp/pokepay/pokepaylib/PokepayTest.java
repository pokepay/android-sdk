package jp.pokepay.pokepaylib;

import org.junit.Test;

import static org.junit.Assert.*;

public class PokepayTest {
    @Test
    public void getAuthorizationUrl() {
        Pokepay.OAuthClient client = new Pokepay.OAuthClient("id", "secret");
        assertEquals(client.getAuthorizationUrl(), "https://www-dev.pokepay.jp/oauth/authorize?client_id=id&response_type=code");
        try {
            assertEquals(client.getAuthorizationUrl("09012345678"),
                    "https://www-dev.pokepay.jp/oauth/authorize?client_id=id&response_type=code&contact=09012345678");
        } catch (ProcessingError processingError) {
            fail();
        }
        try {
            assertEquals(client.getAuthorizationUrl("{}@foo.com"),
                    "https://www-dev.pokepay.jp/oauth/authorize?client_id=id&response_type=code&contact=%7B%7D%40foo.com");
        } catch (ProcessingError processingError) {
            fail();
        }
    }
}