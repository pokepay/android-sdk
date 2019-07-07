package jp.pokepay.pokepaylib;

import android.content.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

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
        private boolean isMerchant;
        private Context context;

        public Client(String accessToken, Context context) {
            this.accessToken = accessToken;
            this.context = context;
            this.isMerchant = false;
        }

        public Client(String accessToken, Context context, boolean isMerchant) {
            this.accessToken = accessToken;
            this.context = context;
            this.isMerchant  = isMerchant;
        }

        public Terminal getTerminalInfo() throws ProcessingError, BankRequestError {
            return new GetTerminal().send(accessToken);
        }

        public TokenInfo getTokenInfo(String token) throws ProcessingError, BankRequestError {
            final Env env = Env.current();
            if (token.startsWith(env.WWW_BASE_URL() + "/cashtrays/")) {
                final String uuid = token.substring((env.WWW_BASE_URL() + "/cashtrays/").length());
                return new TokenInfo(
                    TokenInfo.Type.CASHTRAY,
                    new GetCashtray(uuid).send(accessToken)
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
            } else if (token.matches("^[A-Z0-9]{25}$")) {
                return new TokenInfo(
                    TokenInfo.Type.POKEREGI,
                    null
                );
            } else if (token.matches("^[0-9]{12}$")) {
                return new TokenInfo(
                    TokenInfo.Type.CPM,
                    new GetCpmToken(token).send(accessToken)
                );
            }
            throw new ProcessingError("Unknown token format");
        }

        public UserTransaction scanToken(String token) throws ProcessingError, BankRequestError {
            return scanToken(token, -1);
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
                bleController.write(jwtResult.data, 1000 * 10);
                if (jwtResult.data != null) {
                    return jwtResult.parseAsUserTransaction();
                } else if (jwtResult.error != null) {
                    final BankError err = jwtResult.parseAsAPIError();
                    throw new BankRequestError(0, err);
                } else {
                    final ObjectMapper mapper = new ObjectMapper();
                    final String defaultError = "{\"type\":\"Invalid JSON structure\",\"message\":\"jwt response doesn't have neither data nor error.\"}";
                    final BankError err = mapper.readValue(defaultError, BankError.class);
                    throw new BankRequestError(0, err);
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

        public UserTransaction scanToken(String token, double amount) throws ProcessingError, BankRequestError {
            final Env env = Env.current();
            if (token.startsWith(env.WWW_BASE_URL() + "/cashtrays/")) {
                final String uuid = token.substring((env.WWW_BASE_URL() + "/cashtrays/").length());
                final CreateTransactionWithCashtray createTransactionWithCashtray = new CreateTransactionWithCashtray(uuid, null);
                return createTransactionWithCashtray.send(accessToken);
            }
            else if (token.startsWith(env.WWW_BASE_URL() + "/bills/")) {
                final String uuid = token.substring((env.WWW_BASE_URL() + "/bills/").length());
                final CreateTransactionWithBill createTransactionWithBill = new CreateTransactionWithBill(uuid, null, amount);
                return createTransactionWithBill.send(accessToken);
            }
            else if (token.startsWith(env.WWW_BASE_URL() + "/checks/")) {
                final String uuid = token.substring((env.WWW_BASE_URL() + "/checks/").length());
                final CreateTransactionWithCheck createTransactionWithCheck = new CreateTransactionWithCheck(uuid, null);
                return createTransactionWithCheck.send(accessToken);
            }
            else if (token.matches("^[A-Z0-9]{25}$")) {
                return scanTokenBLE(token);
            }
            else if (token.matches("^[0-9]{12}$")) {
                final CreateTransactionWithCpm createTransactionWithCpm = new CreateTransactionWithCpm(token, null, amount);
                return createTransactionWithCpm.send(accessToken);
            }
            throw new ProcessingError("Unknown token format");
        }

        public String createToken(double amount, String description) throws ProcessingError, BankRequestError {
            return createToken(amount, description,-1, null);
        }

        public String createToken(double amount, String description, int expiresIn) throws ProcessingError, BankRequestError {
            return createToken(amount, description, expiresIn, null);
        }

        public String createToken(double amount, String description, int expiresIn, String accountId) throws ProcessingError, BankRequestError {
            final Env env = Env.current();
            if (isMerchant) {
                CreateCashtray createCashtray = new CreateCashtray(amount, description, expiresIn);
                Cashtray cashtray = createCashtray.send(accessToken);
                return env.WWW_BASE_URL() + "/cashtrays/" + cashtray.id;
            } else if (amount < 0) {
                CreateBill createBill = new CreateBill(-amount, description, accountId);
                Bill bill = createBill.send(accessToken);
                return env.WWW_BASE_URL() + "/bills/" + bill.id;
            } else if (amount > 0) {
                CreateCheck createCheck = new CreateCheck(amount, description, accountId);
                Check check = createCheck.send(accessToken);
                return env.WWW_BASE_URL() + "/checks/" + check.id;
            } else { // amount == 0
                CreateBill createBill = new CreateBill(-1, description, accountId);
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

        public AccessToken getAccessToken(String code) throws ProcessingError, OAuthRequestError {
            ExchangeAuthCode exchangeAuthCode = new ExchangeAuthCode(code, clientId, clientSecret);
            AccessToken accessToken = exchangeAuthCode.send();
            return accessToken;
        }
    }
}
