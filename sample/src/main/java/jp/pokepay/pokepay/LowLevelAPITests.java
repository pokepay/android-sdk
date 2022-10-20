package jp.pokepay.pokepay;


import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import jp.pokepay.pokepaylib.BankAPI.Account.CreateAccount;
import jp.pokepay.pokepaylib.BankAPI.Account.CreateAccountCpmToken;
import jp.pokepay.pokepaylib.BankAPI.Account.GetAccount;
import jp.pokepay.pokepaylib.BankAPI.Account.GetAccountCouponDetail;
import jp.pokepay.pokepaylib.BankAPI.Account.GetAccountCoupons;
import jp.pokepay.pokepaylib.BankAPI.Account.GetAccountTransactions;
import jp.pokepay.pokepaylib.BankAPI.Account.GetAccountBalances;
import jp.pokepay.pokepaylib.BankAPI.Account.PatchAccountCouponDetail;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.BankAPI.Bill.CreateBill;
import jp.pokepay.pokepaylib.BankAPI.Bill.DeleteBill;
import jp.pokepay.pokepaylib.BankAPI.Bill.GetBill;
import jp.pokepay.pokepaylib.BankAPI.Bill.UpdateBill;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.CreateCashtray;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.DeleteCashtray;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.GetCashtray;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.GetCashtrayAttempts;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.UpdateCashtray;
import jp.pokepay.pokepaylib.BankAPI.Check.CreateCheck;
import jp.pokepay.pokepaylib.BankAPI.Check.DeleteCheck;
import jp.pokepay.pokepaylib.BankAPI.Check.GetCheck;
import jp.pokepay.pokepaylib.BankAPI.Check.UpdateCheck;
import jp.pokepay.pokepaylib.BankAPI.CpmToken.GetCpmToken;
import jp.pokepay.pokepaylib.BankAPI.PrivateMoney.GetPrivateMoneyCoupons;
import jp.pokepay.pokepaylib.BankAPI.PrivateMoney.SearchPrivateMoneys;
import jp.pokepay.pokepaylib.BankAPI.Terminal.AddTerminalPublicKey;
import jp.pokepay.pokepaylib.BankAPI.Terminal.GetTerminal;
import jp.pokepay.pokepaylib.BankAPI.Terminal.UpdateTerminal;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithBill;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithCashtray;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithCpm;
import jp.pokepay.pokepaylib.BankAPI.Transaction.GetTransaction;
import jp.pokepay.pokepaylib.BankAPI.User.DeleteUserEmail;
import jp.pokepay.pokepaylib.BankAPI.User.GetUserAccounts;
import jp.pokepay.pokepaylib.BankAPI.User.GetUserTransactions;
import jp.pokepay.pokepaylib.BankAPI.User.RegisterUserEmail;
import jp.pokepay.pokepaylib.BankAPI.User.SendConfirmationEmail;
import jp.pokepay.pokepaylib.Env;
import jp.pokepay.pokepaylib.Parameters.Metadata;
import jp.pokepay.pokepaylib.Parameters.Product;
import jp.pokepay.pokepaylib.Pokepay;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.Account;
import jp.pokepay.pokepaylib.Responses.AccountCpmToken;
import jp.pokepay.pokepaylib.Responses.Bill;
import jp.pokepay.pokepaylib.Responses.Cashtray;
import jp.pokepay.pokepaylib.Responses.CashtrayAttempts;
import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.Responses.Coupon;
import jp.pokepay.pokepaylib.Responses.CouponDetail;
import jp.pokepay.pokepaylib.Responses.NoContent;
import jp.pokepay.pokepaylib.Responses.PaginatedAccountBalances;
import jp.pokepay.pokepaylib.Responses.PaginatedAccounts;
import jp.pokepay.pokepaylib.Responses.PaginatedCoupons;
import jp.pokepay.pokepaylib.Responses.PaginatedPrivateMoneys;
import jp.pokepay.pokepaylib.Responses.PaginatedTransactions;
import jp.pokepay.pokepaylib.Responses.ServerKey;
import jp.pokepay.pokepaylib.Responses.Terminal;
import jp.pokepay.pokepaylib.Responses.UserTransaction;
import jp.pokepay.pokepaylib.TokenInfo;

public class LowLevelAPITests {
    private final String merchantAccessToken = "eYNDMo_cAqPgxMW3qlMv9968awTwFpiwi_rR8XrRhaO6zMOgMfem2q1wfnlluo-v";// 購入店を想定
    private final String customerAccessToken = "S-WAIYRN6rVdb77rYGgMeRQgMLuQ2ZAM0Fo8HfocrrTWxH7tsehCkD6JJSjGhs-0";// 購入客を想定(残高あり)
    private final String pokepayMoneyId = "4b138a4c-8944-4f98-a5c4-96d3c1c415eb";

    public LowLevelAPITests() {
        Pokepay.setEnv(Env.DEVELOPMENT);
    }

    public Product[] getProducts() {
        Product[] products = new Product[3];
        products[0] = Product.create("4569951116179", null, "ハムスこくとろカレー140g", 150, 300, false, 2.0, "個");
        products[1] = Product.create("4569951116179", null, "SCカレーの王様80g", 160, 160, false, 1.0, "個");
        products[2] = Product.create("4569951116179", "4569951116179", "牛肩ロースしゃぶしゃぶ用", 200, 600, false, 3.0, "100グラム");
        return products;
    }

    // 全てのテストを一括で行う //
    public void AllTests() {
        try {
            System.out.println("AccoutTest:" + AccountTest());
            System.out.println("BillTest:" + BillTest());
            System.out.println("CashtrayTest:" + CashtrayTest());
            System.out.println("CheckTest:" + CheckTest());
            System.out.println("PrivateMoneyTest:" + PrivateMoneyTest());
            System.out.println("TerminalTest:" + TerminalTest("pubkey"));
            System.out.println("TransactionTest:" + TransactionTest());
            System.out.println("UserTest:" + UserTest("xxxxxxxxx@xxxx.xxx"));
            System.out.println("CpmToken:" + CpmTest());
            System.out.println("parseAsPokeregiTokenTest:" + ParseAsPokeregiTokenTest());
        } catch (BankRequestError e) {
            System.out.println("Oh no BankRequstError occurred");
            System.out.println(e.toString());
        } catch (ProcessingError e){
            System.out.println("Oh no ProcessingError occurred");
            System.out.println(e.toString());
        }
    }


    public String AccountTest() throws BankRequestError, ProcessingError {
        // privateMoneyIdでアカウントの作成 //
        CreateAccount createAccount = new CreateAccount("accountTest", pokepayMoneyId, null);
        Account account = createAccount.send(merchantAccessToken);
        // アカウントの確認 //
        GetAccount getAccount = new GetAccount(account.id);
        account = getAccount.send(merchantAccessToken);
        // アカウントidからTransactionの取得 //
        GetAccountTransactions getAccountTransactions = new GetAccountTransactions(account.id, null, null, 10);
        PaginatedTransactions paginatedTransactions = getAccountTransactions.send(merchantAccessToken);
        // アカウントidからBalancesの確認 //
        GetAccountBalances getBalances = new GetAccountBalances(account.id, null, null, 10);
        PaginatedAccountBalances paginatedAccountBalances = getBalances.send(merchantAccessToken);
        return "OK";
    }

    public String BillTest() throws BankRequestError, ProcessingError {
        // Billの作成 //
        CreateBill createBill = new CreateBill(1.0, null, "bill test", null);
        Bill bill = createBill.send(merchantAccessToken);
        // Billの確認 //
        GetBill getBill = new GetBill(bill.id);
        bill = getBill.send(merchantAccessToken);
        // Billの支払いを2円に変更 //
        UpdateBill updateBill = new UpdateBill(bill.id, 2.0, "bill update");
        bill = updateBill.send(merchantAccessToken);
        // Billの消去 //
        DeleteBill deleteBill = new DeleteBill(bill.id);
        NoContent nc = deleteBill.send(merchantAccessToken);
        // 消去できているかの確認 //
        getBill = new GetBill(bill.id);
        bill = getBill.send(merchantAccessToken);
        // with products
        // Billの作成 //
        createBill = new CreateBill(1.0, null, "bill test", getProducts());
        bill = createBill.send(merchantAccessToken);
        // Billの確認 //
        getBill = new GetBill(bill.id);
        bill = getBill.send(merchantAccessToken);
        // Billの支払いを2円に変更 //
        updateBill = new UpdateBill(bill.id, 2.0, "bill update");
        bill = updateBill.send(merchantAccessToken);
        // Billの消去 //
        deleteBill = new DeleteBill(bill.id);
        nc = deleteBill.send(merchantAccessToken);
        return "OK";
    }

    public String CashtrayTest() throws BankRequestError, ProcessingError {
        System.out.println("Cashtrayの作成");
        CreateCashtray createCashtray = new CreateCashtray(1.0, "cashtray test", null, null);
        Cashtray cashtray = createCashtray.send(merchantAccessToken);
        System.out.println("cashtray created " + cashtray.id);
        System.out.println("Cashtrayの作成 // with-products");
        createCashtray = new CreateCashtray(1.0, "cashtray test", null, getProducts());
        cashtray = createCashtray.send(merchantAccessToken);
        System.out.println("cashtray created " + cashtray.id);
        System.out.println("Cashtrayの確認");
        GetCashtray getCashtray = new GetCashtray(cashtray.id);
        cashtray = getCashtray.send(merchantAccessToken);
        System.out.println("cashtray got " + cashtray.id);
        System.out.println("Cashtrayのチャージを2円に変更");
        UpdateCashtray updateCashtray = new UpdateCashtray(cashtray.id, 2.0, "cashtray update", null);
        cashtray = updateCashtray.send(merchantAccessToken);
        System.out.println("cashtray updated " + cashtray.id);
        System.out.println("Cashtrayの消去");
        DeleteCashtray deleteCashtray = new DeleteCashtray(cashtray.id);
        NoContent nc = deleteCashtray.send(merchantAccessToken);
        System.out.println("cashtray deleted " + cashtray.id);
        System.out.println("消去できているかの確認");
        getCashtray = new GetCashtray(cashtray.id);
        try {
            cashtray = getCashtray.send(merchantAccessToken);
            // If it success its wrong.
            throw new ProcessingError("cashtray delete failed " + cashtray.toString());
        } catch (BankRequestError e) {
            if (e.statusCode == 404) {
                System.out.println("cashtray NotFound " + cashtray.id);
            } else {
                throw e;
            }
        }
        System.out.println("Cashtrayの作成 // (1万円支払い)");
        createCashtray = new CreateCashtray(-10000, "cashtray test", null, getProducts());
        cashtray = createCashtray.send(merchantAccessToken);
        System.out.println("cashtray created " + cashtray.id);
        System.out.println("CashtrayAttempts取得 => null");
        GetCashtrayAttempts getCashtrayAttempts = new GetCashtrayAttempts(cashtray.id);
        CashtrayAttempts attempts = getCashtrayAttempts.send(merchantAccessToken);
        if (attempts.rows.length != 0) {
            throw new Error("attempts should be 0");
        }
        System.out.println("決済しようとする => 失敗");
        CreateTransactionWithCashtray createTransactionWithCashtray = new CreateTransactionWithCashtray(cashtray.id, null,null, null);
        try {
            UserTransaction transaction = createTransactionWithCashtray.send(customerAccessToken);
        } catch (BankRequestError e) {
            if (e.statusCode != 422) {
                throw e;
            }
        }
        System.out.println("CashtrayAttempts取得 => 1個取得");
        getCashtrayAttempts = new GetCashtrayAttempts(cashtray.id);
        attempts = getCashtrayAttempts.send(merchantAccessToken);
        if (attempts.rows.length != 1) {
            throw new Error("attempts should be 1");
        }
        if (attempts.rows[0].status_code != 422) {
            throw new Error("status code not matched");
        }
        System.out.println("Cashtrayをチャージ1円に変更");
        updateCashtray = new UpdateCashtray(cashtray.id, 1.0, "cashtray update", null);
        cashtray = updateCashtray.send(merchantAccessToken);
        System.out.println("cashtray updated " + cashtray.id);
        System.out.println("決済しようとする => 成功");
        createTransactionWithCashtray = new CreateTransactionWithCashtray(cashtray.id, null,null, null);
        UserTransaction transaction = createTransactionWithCashtray.send(customerAccessToken);
        System.out.println("CashtrayAttempts取得 => 2個取得");
        getCashtrayAttempts = new GetCashtrayAttempts(cashtray.id);
        attempts = getCashtrayAttempts.send(merchantAccessToken);
        if (attempts.rows.length != 2) {
            throw new Error("attempts should be 1");
        }
        if (attempts.rows[0].status_code != 200) {
            throw new Error("status code not matched");
        }
        return "OK";
    }

    public String CheckTest() throws BankRequestError, ProcessingError {
        // Checkの作成 //
        CreateCheck createCheck = new CreateCheck(1.0, null, "check test", null);
        Check check = createCheck.send(merchantAccessToken);
        System.out.println("check created " + check.id);
        // Checkの確認 //
        GetCheck getCheck = new GetCheck(check.id);
        check = getCheck.send(merchantAccessToken);
        if (check.is_disabled == true) {
            throw new ProcessingError("Disabled check");
        }
        System.out.println(check.point_expires_at);
        System.out.println(check.point_expires_in_days);
        System.out.println("check got " + check.id);
        // Checkの支払いを2円に変更 //
        UpdateCheck updateCheck = new UpdateCheck(check.id, 2.0, "check update");
        check = updateCheck.send(merchantAccessToken);
        if (check.amount != 2.0) {
            throw new ProcessingError("Update failed");
        }
        System.out.println("check updated " + check.id);
        // Checkの消去 //
        DeleteCheck deleteCheck = new DeleteCheck(check.id);
        NoContent nc = deleteCheck.send(merchantAccessToken);
        System.out.println("check deleted" + check.id);
        // 消去できているかの確認 //
        getCheck = new GetCheck(check.id);
        check = getCheck.send(merchantAccessToken);
        if (check.is_disabled == false) {
            throw new ProcessingError("Disabling failed");
        }
        return "OK";
    }

    public String PrivateMoneyTest() throws BankRequestError, ProcessingError {
        // フィルタ無しで全て確認 //
        SearchPrivateMoneys searchPrivateMoneys = new SearchPrivateMoneys(null, true, null, null, 100);
        PaginatedPrivateMoneys paginatedPrivateMoney = searchPrivateMoneys.send(customerAccessToken);
        int noFilteredCount = paginatedPrivateMoney.count;
        // フィルタあり（部分一致）で確認 //
        searchPrivateMoneys = new SearchPrivateMoneys("コイルマネー", true, null, null, 100);
        paginatedPrivateMoney = searchPrivateMoneys.send(customerAccessToken);
        int filteredCount = paginatedPrivateMoney.count;
        if (filteredCount >= noFilteredCount) {
            throw new ProcessingError("filteredCount is less than noFilteredCount. its suspicious.");
        }
        return "OK";
    }

    public String TerminalTest(String pubkey) throws BankRequestError, ProcessingError {
        // TerminalInfoの取得 //
        GetTerminal getTerminal = new GetTerminal();
        Terminal terminal =  getTerminal.send(merchantAccessToken);
        // Terminalの名前とトークンを変更 //
        UpdateTerminal updateTerminal = new UpdateTerminal(terminal.account.id , "term00", "hoge");
        terminal = updateTerminal.send(merchantAccessToken);
        // pubkeyの追加　　　注意：pubkeyは毎回違うものを入れる必要あり //
        AddTerminalPublicKey addTerminalPublicKey = new AddTerminalPublicKey(pubkey);
        ServerKey key = addTerminalPublicKey.send(merchantAccessToken);
        return "OK";
    }

    public String TransactionTest() throws BankRequestError, ProcessingError {
        UserTransaction userTransaction = null;
        String ret = null;
        // TerminalInfoの取得 //
        GetTerminal getTerminal = new GetTerminal();
        Terminal terminal = getTerminal.send(customerAccessToken);
        System.out.println("terminal info got " + terminal.toString());
        // Billの作成 //
        CreateBill createBill = new CreateBill(1.0, null, null, null);
        Bill bill = createBill.send(merchantAccessToken);
        System.out.println("bill created " + bill.toString());
        // 上記のBillで取引を作成 //
        CreateTransactionWithBill createTransactionWithBill = new CreateTransactionWithBill(bill.id, null, 1.0,null, null);
        userTransaction = createTransactionWithBill.send(customerAccessToken);
        System.out.println("transaction created " + userTransaction.toString());
        // 取引の確認 //
        GetTransaction getTransaction = new GetTransaction(userTransaction.id);
        userTransaction = getTransaction.send(customerAccessToken);
        System.out.println("transaction got " + userTransaction.toString());
        getTransaction = new GetTransaction(userTransaction.id);
        userTransaction = getTransaction.send(customerAccessToken);
        System.out.println("transaction canceled checked" + userTransaction.toString());
        return "OK";
    }

    public String UserTest(String email) throws BankRequestError, ProcessingError {
        String str;
        // Terminalの取得 //
        GetTerminal getTerminal = new GetTerminal();
        Terminal terminal = getTerminal.send(customerAccessToken);
        System.out.println("terminal info got " + terminal.toString());
        // TerminalからuserIdを取得してUserの確認 //
        GetUserAccounts getUserAccounts = new GetUserAccounts(terminal.user.id,null,null,1);
        PaginatedAccounts paginatedAccounts = getUserAccounts.send(customerAccessToken);
        System.out.println("account info got " + paginatedAccounts.toString());
        // TerminalからUserIdを取得してTransactionsの確認 //
        GetUserTransactions getUserTransactions = new GetUserTransactions(terminal.user.id,null,null,1);
        PaginatedTransactions paginatedTransactions = getUserTransactions.send(customerAccessToken);
        System.out.println("transactions got " + paginatedTransactions.toString());
        // アドレスの登録
        RegisterUserEmail registerUserEmail = new RegisterUserEmail(email);
        NoContent nc = registerUserEmail.send(customerAccessToken);
        System.out.println("added Email Address");
        // 確認メールの送信 //
        SendConfirmationEmail sendConfirmationEmail = new SendConfirmationEmail(terminal.user.id, email);
        nc = sendConfirmationEmail.send(customerAccessToken);
        System.out.println("Check mail sent");
        // 登録アドレスの削除 //
        DeleteUserEmail deleteUserEmail = new DeleteUserEmail(terminal.user.id, email);
        nc = deleteUserEmail.send(customerAccessToken);
        System.out.println("mail delete");
        // この後にメール承認必要 //
        return "OK";
    }

    public String CpmTest() throws BankRequestError, ProcessingError {

        System.out.println("Cpmをデタラメにgetして404");
        try {
            GetCpmToken getCpmToken = new GetCpmToken("90000022000011112222");
            getCpmToken.send(customerAccessToken);
            throw new ProcessingError("this call should be fail");
        } catch (BankRequestError e) {
            if (e.statusCode != 404) {
                throw e;
            }
        }

        System.out.println("客のアクセストークンでCpmをcreate");
        CreateAccount createAccount = new CreateAccount("accountTest", pokepayMoneyId, null);
        Account account = createAccount.send(customerAccessToken);
        CreateAccountCpmToken createAccountCpmToken = new CreateAccountCpmToken(account.id, CreateAccountCpmToken.SCOPE_BOTH, 100, new Metadata(new HashMap<String, String>()));
        AccountCpmToken cpmToken = createAccountCpmToken.send(customerAccessToken);

        System.out.println("客のアクセストークンでCpmをget");
        GetCpmToken getCpmToken = new GetCpmToken(cpmToken.cpm_token);
        cpmToken = getCpmToken.send(customerAccessToken);
        if (cpmToken.transaction != null) {
            throw new ProcessingError("transaction should be null.");
        }
        SystemClock.sleep(50);
        System.out.println("客のアクセストークンでCpmをcreate");
        Map<String, String> metadataMap = new HashMap<String, String>();
        metadataMap.put("foo", "bar");
        CreateAccountCpmToken createAccountCpmToken2 = new CreateAccountCpmToken(account.id, CreateAccountCpmToken.SCOPE_BOTH, 100, new Metadata(metadataMap));
        AccountCpmToken cpmToken2 = createAccountCpmToken2.send(customerAccessToken);
        System.out.printf(cpmToken2.toString());

        SystemClock.sleep(50);
        System.out.println("客のアクセストークンでCpmをget、前のCpmがinvalidなことを確認");
        try {
            cpmToken = getCpmToken.send(customerAccessToken);
            throw new ProcessingError("this call should be fail " + getCpmToken.cpmToken);
        } catch (BankRequestError e) {
            if (e.statusCode != 404) {
                throw e;
            }
        }

        System.out.println("客のアクセストークンでCpmをget、transactionがnullなことを確認");
        GetCpmToken getCpmToken2 = new GetCpmToken(cpmToken2.cpm_token);
        cpmToken2 = getCpmToken2.send(customerAccessToken);
        if (cpmToken2.transaction != null) {
            throw new ProcessingError("transaction should be null.");
        }

        System.out.println("店のアクセストークンでCpmにtopup");
        SystemClock.sleep(50);
        CreateTransactionWithCpm createTransactionWithCpm = new CreateTransactionWithCpm(cpmToken2.cpm_token, null, 100.0, null);
        createTransactionWithCpm.send(merchantAccessToken);

        System.out.println("客のアクセストークンでCpmをget、transaction確認");
        cpmToken2 = getCpmToken2.send(customerAccessToken);
        if (cpmToken2.transaction == null) {
            throw new ProcessingError("transaction should not be null.");
        }

        System.out.println("残高確認");
        Account account2 = new GetAccount(account.id).send(customerAccessToken);
        if (account.balance + 100 != account2.balance) {
            throw new ProcessingError("account wasn't topped up.");
        }

        System.out.println("客のアクセストークンでCpmをcreate");
        Map<String, String> metadataMap2 = new HashMap<String, String>();
        metadataMap2.put("baz","qux");
        metadataMap2.put("foo", "bar");
        CreateAccountCpmToken createAccountCpmToken3 = new CreateAccountCpmToken(account.id, CreateAccountCpmToken.SCOPE_BOTH, 100, new Metadata(metadataMap2));
        AccountCpmToken cpmToken3 = createAccountCpmToken3.send(customerAccessToken);

        System.out.println("店のアクセストークンでCpmにpayment with products");
        SystemClock.sleep(50);
        CreateTransactionWithCpm createTransactionWithCpm2 = new CreateTransactionWithCpm(cpmToken3.cpm_token, null, -100.0, getProducts());
        createTransactionWithCpm2.send(merchantAccessToken);

        System.out.println("客のアクセストークンでCpmをget、transaction確認");
        GetCpmToken getCpmToken3 = new GetCpmToken(cpmToken3.cpm_token);
        cpmToken3 = getCpmToken3.send(customerAccessToken);
        if (cpmToken3.transaction == null) {
            throw new ProcessingError("transaction should not be null.");
        }

        System.out.println("残高確認");
        Account account3 = new GetAccount(account.id).send(customerAccessToken);
        if (account.balance != account3.balance) {
            throw new ProcessingError("account wasn't matched.");
        }

        return "OK";
    }

    public String ParseAsPokeregiTokenTest() throws ProcessingError, BankRequestError {
        Pokepay.setEnv(Env.DEVELOPMENT);
        Pokepay.Client client = new Pokepay.Client(customerAccessToken, null);
        final String key = "A0B1C2D3E4F5G6H7I8J9K0L1M";
        String v1QRToken = client.parseAsPokeregiToken(key);
        if (!v1QRToken.equals(key)) {
            throw new ProcessingError("V1 QR should be matched");
        }
        String v1NFCToken = client.parseAsPokeregiToken("https://www.pokepay.jp/pd?d=" + key);
        if (!v1NFCToken.equals(key)) {
            throw new ProcessingError("V1 NFC should be matched");
        }
        String v2QRNFCToken = client.parseAsPokeregiToken("https://www.pokepay.jp/pd/" + key);
        if (!v2QRNFCToken.equals(key)) {
            throw new ProcessingError("V2 QR/NFC should be matched");
        }
        String invalidToken = client.parseAsPokeregiToken("ABCDEFG10102020202020");
        if (!invalidToken.equals("")) {
            throw new ProcessingError("invalid token shouldn't be matched");
        }
        TokenInfo info1 = client.getTokenInfo("https://www.pokepay.jp/pd?d=" + key);
        if (info1.type != TokenInfo.Type.POKEREGI) {
            throw new ProcessingError("V1 NFC should be matched");
        }
        TokenInfo info2 = client.getTokenInfo("https://www.pokepay.jp/pd/" + key);
        if (info2.type != TokenInfo.Type.POKEREGI) {
            throw new ProcessingError("V2 QR/NFC should be matched");
        }
        String errorMessage = "";
        try {
            TokenInfo info3 = client.getTokenInfo("ABCDEFG10102020202020");
        } catch (ProcessingError e) {
            errorMessage = e.getMessage();
        }
        if (errorMessage != "Unknown token format") {
            throw new ProcessingError("invalid token shouldn't be matched");
        }
        return "OK";
    }

    public String ConfirmNewParametersShouldBeIgnoredTest() throws ProcessingError, BankRequestError {
        // APIに知らないパラメータがあっても無視してくれることをテスト
        class GetAccount extends BankRequest {
            @NonNull
            public String id;
            public GetAccount(String id){
                this.id = id;
            }
            protected final String path() {
                return "/accounts/" + id;
            }
            protected final Request.Method method() {
                return Request.Method.GET;
            }
            public final SubsetOfAccount send(String accessToken) throws ProcessingError, BankRequestError {
                return super.send(SubsetOfAccount.class, accessToken);
            }
        };
        Pokepay.setEnv(Env.DEVELOPMENT);
        Pokepay.Client client = new Pokepay.Client(customerAccessToken, null);
        CreateAccount createAccount = new CreateAccount("accountTest", pokepayMoneyId, null);
        GetAccount getAccount = new GetAccount(createAccount.send(customerAccessToken).id);
        SubsetOfAccount account = getAccount.send(customerAccessToken);
        System.out.println(account.id);
        return "OK";
    }

    public  String CouponTest() throws ProcessingError, BankRequestError{
        Pokepay.Client merchantClient = new Pokepay.Client(merchantAccessToken, null);
        Pokepay.Client customerClient = new Pokepay.Client(customerAccessToken, null);
        //list of coupons can receive
        PaginatedCoupons paginatedCoupons= new GetPrivateMoneyCoupons(pokepayMoneyId,null, null,100).send(customerAccessToken);
        System.out.println("paginated coupons: "+paginatedCoupons);
        System.out.println("coupons count: "+paginatedCoupons.count);
        Coupon coupon = paginatedCoupons.items[0];
        System.out.println("Coupon: "+coupon);
        CouponDetail couponDetail = new GetAccountCouponDetail(customerClient.getTerminalInfo().account.id, coupon.id).send(customerAccessToken);
        System.out.println("Coupon detail: "+couponDetail);

        CouponDetail patchCouponDetail = new PatchAccountCouponDetail(customerClient.getTerminalInfo().account.id, coupon.id,true, null).send(customerAccessToken);
        System.out.println("Patch coupon detail: "+patchCouponDetail);
        Account account = new GetAccount(customerClient.getTerminalInfo().account.id).send(customerAccessToken);
        System.out.println("client account: "+account);
        Account merchantAccount = new GetAccount(merchantClient.getTerminalInfo().account.id).send(merchantAccessToken);
        System.out.println("merchant account: "+merchantAccount);

        PaginatedCoupons accountPaginatedCoupons = new GetAccountCoupons(merchantClient.getTerminalInfo().account.id,false, null, null, 100).send(merchantAccessToken);
        System.out.println("Account paginated coupons count: "+accountPaginatedCoupons.count);

        return "OK";
    }

}
