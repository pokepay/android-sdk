package jp.pokepay.pokepaylib;

import jp.pokepay.pokepaylib.BankAPI.Bill.CreateBill;
import jp.pokepay.pokepaylib.BankAPI.Bill.GetBill;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.CreateCashtray;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.GetCashtray;
import jp.pokepay.pokepaylib.BankAPI.Check.CreateCheck;
import jp.pokepay.pokepaylib.BankAPI.Check.GetCheck;
import jp.pokepay.pokepaylib.BankAPI.Terminal.GetTerminal;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithBill;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithCashtray;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithCheck;
import jp.pokepay.pokepaylib.OAuthAPI.Token.ExchangeAuthCode;
import jp.pokepay.pokepaylib.Responses.AccessToken;
import jp.pokepay.pokepaylib.Responses.Bill;
import jp.pokepay.pokepaylib.Responses.Cashtray;
import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.Responses.Terminal;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class Pokepay {
    public Client client;
    public OAuthClient oAuthClient;

    private Constants constants = new Constants();

    public Pokepay(){

    }

    public void NewClient(String accessToken){
        NewClient(accessToken, false);
    }
    public void NewClient(String accessToken, boolean isMerchant){
        this.client = new Client(accessToken, isMerchant);
    }

    public void NewOAuthClient(String clientId, String clientSecret){
        this.oAuthClient = new OAuthClient(clientId, clientSecret);
    }

    public class Client {
        private String accessToken;
        private boolean isMerchant;

        public Client(String accessToken, boolean isMerchant){
            this.accessToken = accessToken;
            this.isMerchant  = isMerchant;
        }

        public Terminal getTerminalInfo(){
            GetTerminal getTerminal = new GetTerminal();
            Terminal terminal = getTerminal.send(accessToken);
            return terminal;
        }

        public Object getTokenInfo(String token){
            // FIXME: FUCK. DONT USE OBJECT.
            Object retInfo = null;
            if(token.startsWith(constants.WWW_BASE_URL + "/cashtrays/")){
                String uuid = token.substring((constants.API_BASE_URL + "/cashtrays/").length());
                GetCashtray getCashtray = new GetCashtray(uuid);
                retInfo = getCashtray.send(accessToken);

            }
            else if(token.startsWith(constants.WWW_BASE_URL + "/bills/")){
                String uuid = token.substring((constants.WWW_BASE_URL + "/bills/").length());
                GetBill getBill = new GetBill(uuid);
                retInfo = getBill.send(accessToken);
            }
            else if(token.startsWith(constants.WWW_BASE_URL + "/checks/")){
                String uuid = token.substring((constants.WWW_BASE_URL + "/checks/").length());
                GetCheck getCheck = new GetCheck(uuid);
                retInfo = getCheck.send(accessToken);
            }
            return retInfo;
        }

        public UserTransaction scanToken(String token){
            return scanToken(token, -1);
        }
        public UserTransaction scanToken(String token, double amount) {
            return scanToken(token, amount, null);
        }
        public UserTransaction scanToken(String token, double amount, String accountId){
            UserTransaction userTransaction = null;
            if(token.startsWith(constants.WWW_BASE_URL + "/cashtrays/")){
                String uuid = token.substring((constants.WWW_BASE_URL + "/cashtrays/").length());
                CreateTransactionWithCashtray createTransactionWithCashtray = new CreateTransactionWithCashtray(uuid, accountId);
                userTransaction = createTransactionWithCashtray.send(accessToken);
            }
            else if(token.startsWith(constants.WWW_BASE_URL + "/bills/")){
                String uuid = token.substring((constants.WWW_BASE_URL + "/bills/").length());
                CreateTransactionWithBill createTransactionWithBill = new CreateTransactionWithBill(uuid, accountId, amount);
                userTransaction = createTransactionWithBill.send(accessToken);
            }
            else if(token.startsWith(constants.WWW_BASE_URL + "/checks/")){
                String uuid = token.substring((constants.WWW_BASE_URL + "/checks/").length());
                CreateTransactionWithCheck createTransactionWithCheck = new CreateTransactionWithCheck(uuid, accountId);
                userTransaction = createTransactionWithCheck.send(accessToken);
            }
            else{
                //invalid token

            }
            return userTransaction;
        }

        public Object createToken(double amount, String description) {
            return createToken(amount, description,-1);
        }
        public Object createToken(double amount, String description, int expiresIn) {
            return createToken(amount, description, expiresIn, null);
        }
        public Object createToken(double amount, String description, int expiresIn, String accountId) {
            // FIXME: FUCK Object.
            if(isMerchant){
                CreateCashtray createCashtray = new CreateCashtray(amount, description, expiresIn);
                Cashtray cashtray = createCashtray.send(accessToken);
                return cashtray;
            }
            else if(amount < 0){
                CreateBill createBill = new CreateBill(amount*(-1), description, accountId);
                Bill bill = createBill.send(accessToken);
                return bill;
            }
            else{
                CreateCheck createCheck = new CreateCheck(amount, description, accountId);
                Check check = createCheck.send(accessToken);
                return check;
            }
        }
    }

    public class OAuthClient {
        private String clientId;
        private String clientSecret;

        public OAuthClient(String clientId, String clientSecret){
            this.clientId = clientId;
            this.clientSecret = clientSecret;
        }

        public String getAuthorizationUrl(){
            return constants.WWW_BASE_URL + "/oauth/authorize?client_id=" + clientId + "&response_type=code";
        }

        public AccessToken getAccessToken(String code){
            ExchangeAuthCode exchangeAuthCode = new ExchangeAuthCode(code, clientId, clientSecret);
            AccessToken accessToken = exchangeAuthCode.send();
            return accessToken;
        }
    }
}
