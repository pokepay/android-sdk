package jp.pokepay.pokepaylib;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.BankAPI.Bill.CreateBill;
import jp.pokepay.pokepaylib.BankAPI.Bill.GetBill;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.CreateCashtray;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.GetCashtray;
import jp.pokepay.pokepaylib.BankAPI.Check.CreateCheck;
import jp.pokepay.pokepaylib.BankAPI.Check.GetCheck;
import jp.pokepay.pokepaylib.BankAPI.CpmToken.GetCpmToken;
import jp.pokepay.pokepaylib.BankAPI.Terminal.GetTerminal;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithBill;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithCashtray;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithCheck;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithCpm;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithJwt;
import jp.pokepay.pokepaylib.OAuthAPI.OAuthRequestError;
import jp.pokepay.pokepaylib.OAuthAPI.Token.ExchangeAuthCode;
import jp.pokepay.pokepaylib.Parameters.Product;
import jp.pokepay.pokepaylib.Pokeregi.BLEController;
import jp.pokepay.pokepaylib.Responses.AccessToken;
import jp.pokepay.pokepaylib.Responses.BankError;
import jp.pokepay.pokepaylib.Responses.Bill;
import jp.pokepay.pokepaylib.Responses.Cashtray;
import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.Responses.JwtResult;
import jp.pokepay.pokepaylib.Responses.Terminal;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class Pokepay {

    public static void setEnv(Env env) {
        Env.setCurrent(env);
    }

    public static class Client {
        private String accessToken;
        private Boolean isMerchant;
        private Context context;

        public Client(String accessToken, Context context) {
            this.accessToken = accessToken;
            this.context = context;
            this.isMerchant = false;
        }

        public Client(String accessToken, Context context, Boolean isMerchant) {
            this.accessToken = accessToken;
            this.context = context;
            this.isMerchant  = isMerchant;
        }

        public Terminal getTerminalInfo() throws ProcessingError, BankRequestError {
            return new GetTerminal().send(accessToken);
        }

        public String parseAsPokeregiToken(String token) {
            // * {25 ALNUM} - (Pokeregi_V1 OfflineMode QR)
            final Pattern V1_QR_REG = Pattern.compile("^([0-9A-Z]{25})$");
            // * https://www.pokepay.jp/pd?={25 ALNUM} - (Pokeregi_V1 OfflineMode NFC)
            // * https://www.pokepay.jp/pd/{25 ALNUM}  - (Pokeregi_V2 OfflineMode QR & NFC)
            final Pattern V1_NFC_V2_QR_NFC_REG = Pattern.compile("^https://www(?:-dev|-sandbox|-qa|)\\.pokepay\\.jp/pd(?:/|\\?d=)([0-9A-Z]{25})$");
            // matching
            final Matcher v1 = V1_QR_REG.matcher(token);
            if (v1.find()) {
                return v1.group(1);
            }
            final Matcher v2 = V1_NFC_V2_QR_NFC_REG.matcher(token);
            if (v2.find()) {
                return v2.group(1);
            }
            return "";
        }

        public TokenInfo getTokenInfo(String token) throws ProcessingError, BankRequestError {
            final Env env = Env.current();
            if (token.startsWith(env.WWW_BASE_URL() + "/cashtrays/")) {
                final String uuid = token.substring((env.WWW_BASE_URL() + "/cashtrays/").length());
                return new TokenInfo(
                    TokenInfo.Type.CASHTRAY,
                    null
                );
            } else if (token.startsWith(env.WWW_BASE_URL() + "/bills/")) {
                final String uuid = token.substring((env.WWW_BASE_URL() + "/bills/").length());
                return new TokenInfo(
                    TokenInfo.Type.BILL,
                    new GetBill(uuid).send(accessToken)
                );
            } else if (token.startsWith(env.WWW_BASE_URL() + "/checks/")) {
                final String uuid = token.substring((env.WWW_BASE_URL() + "/checks/").length());
                return new TokenInfo(
                    TokenInfo.Type.CHECK,
                    new GetCheck(uuid).send(accessToken)
                );
            } else if (token.matches("^[0-9]{20}$")) {
                return new TokenInfo(
                    TokenInfo.Type.CPM,
                    new GetCpmToken(token).send(accessToken)
                );
            } else {
                String key = parseAsPokeregiToken(token);
                if (key.length() > 0) {
                    return new TokenInfo(
                        TokenInfo.Type.POKEREGI,
                        null
                    );
                }
            }
            throw new ProcessingError("Unknown token format");
        }

        public UserTransaction scanToken(String token) throws ProcessingError, BankRequestError {
            return scanToken(token, null, null, null);
        }

        public UserTransaction scanToken(String token, Double amount, String accountId, Product[] products) throws ProcessingError, BankRequestError {
            final Env env = Env.current();
            if (token.startsWith(env.WWW_BASE_URL() + "/cashtrays/")) {
                final String uuid = token.substring((env.WWW_BASE_URL() + "/cashtrays/").length());
                final CreateTransactionWithCashtray createTransactionWithCashtray = new CreateTransactionWithCashtray(uuid, accountId);
                return createTransactionWithCashtray.send(accessToken);
            }
            else if (token.startsWith(env.WWW_BASE_URL() + "/bills/")) {
                final String uuid = token.substring((env.WWW_BASE_URL() + "/bills/").length());
                final CreateTransactionWithBill createTransactionWithBill = new CreateTransactionWithBill(uuid, accountId, amount);
                return createTransactionWithBill.send(accessToken);
            }
            else if (token.startsWith(env.WWW_BASE_URL() + "/checks/")) {
                final String uuid = token.substring((env.WWW_BASE_URL() + "/checks/").length());
                final CreateTransactionWithCheck createTransactionWithCheck = new CreateTransactionWithCheck(uuid, accountId);
                return createTransactionWithCheck.send(accessToken);
            }
            else if (token.matches("^[0-9]{20}$")) {
                final CreateTransactionWithCpm createTransactionWithCpm = new CreateTransactionWithCpm(token, accountId, amount, products);
                return createTransactionWithCpm.send(accessToken);
            }
            else {
                String key = parseAsPokeregiToken(token);
                if (key.length() > 0) {
                    return scanTokenBLE(key);
                }
            }
            throw new ProcessingError("Unknown token format");
        }

        private UserTransaction scanTokenBLE(String token) throws ProcessingError, BankRequestError {
            if (context == null) {
                throw new ProcessingError("Scanning to pokeregi requires Context (for BLE)");
            }
            BLEController bleController = null;
            try {
                bleController = new BLEController(token, context);
                bleController.connect(1000 * 10);
                final String jwt = bleController.read(1000 * 10);
                final CreateTransactionWithJwt createTransactionWithJwt = new CreateTransactionWithJwt(jwt, null);
                final JwtResult jwtResult = createTransactionWithJwt.send(accessToken);
                if (jwtResult.data != null) {
                    bleController.write(jwtResult.data, 1000 * 10);
                    return jwtResult.parseAsUserTransaction();
                } else if (jwtResult.error != null) {
                    bleController.write(jwtResult.error, 1000 * 10);
                    final BankError err = jwtResult.parseAsAPIError();
                    throw new BankRequestError(999, err);
                } else {
                    final ObjectMapper mapper = new ObjectMapper();
                    final String defaultError = "{\"type\":\"Invalid JSON structure\",\"message\":\"jwt response doesn't have neither data nor error.\"}";
                    final BankError err = mapper.readValue(defaultError, BankError.class);
                    throw new BankRequestError(999, err);
                }
            } catch (ProcessingError e) {
                throw e;
            } catch (BankRequestError e) {
                throw e;
            } catch (Exception e) {
                throw new ProcessingError(e.getMessage());
            } finally {
                if (bleController != null) {
                    bleController.disconnect();
                }
            }
        }

        public String createToken(Double amount, String description) throws ProcessingError, BankRequestError {
            return createToken(amount, description, null, null, null);
        }

        public String createToken(Double amount, String description, Integer expiresIn) throws ProcessingError, BankRequestError {
            return createToken(amount, description, expiresIn, null, null);
        }

        public String createToken(Double amount, String description, Integer expiresIn, String accountId) throws ProcessingError, BankRequestError {
            return createToken(amount, description, expiresIn, accountId, null);
        }

        public String createToken(Double amount, String description, Integer expiresIn, String accountId, Product[] products) throws ProcessingError, BankRequestError {
            final Env env = Env.current();
            if (isMerchant) {
                CreateCashtray createCashtray = new CreateCashtray(amount, description, expiresIn, products);
                Cashtray cashtray = createCashtray.send(accessToken);
                return env.WWW_BASE_URL() + "/cashtrays/" + cashtray.id;
            } else if (amount != null) {
                if (amount < 0) {
                    CreateBill createBill = new CreateBill(-amount, accountId, description, products);
                    Bill bill = createBill.send(accessToken);
                    return env.WWW_BASE_URL() + "/bills/" + bill.id;
                } else {
                    CreateCheck createCheck = new CreateCheck(amount, accountId, description);
                    Check check = createCheck.send(accessToken);
                    return env.WWW_BASE_URL() + "/checks/" + check.id;
                }
            } else { // amount == null
                CreateBill createBill = new CreateBill(amount, accountId, description, products);
                Bill bill = createBill.send(accessToken);
                return env.WWW_BASE_URL() + "/bills/" + bill.id;
            }
        }
    }

    public static class OAuthClient {
        private String clientId;
        private String clientSecret;

        public OAuthClient(String clientId, String clientSecret) {
            this.clientId = clientId;
            this.clientSecret = clientSecret;
        }

        public String getAuthorizationUrl() {
            return Env.current().WWW_BASE_URL() + "/oauth/authorize?client_id=" + clientId + "&response_type=code";
        }

        public String getAuthorizationUrl(String contact) throws ProcessingError {
            try {
                return getAuthorizationUrl() + "&contact=" + URLEncoder.encode(contact, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new ProcessingError(e.getMessage());
            }
        }

        public AccessToken getAccessToken(String code) throws ProcessingError, OAuthRequestError {
            ExchangeAuthCode exchangeAuthCode = new ExchangeAuthCode(code, clientId, clientSecret);
            AccessToken accessToken = exchangeAuthCode.send();
            return accessToken;
        }
    }
}
