package jp.pokepay.pokepay;


import android.os.SystemClock;

import jp.pokepay.pokepaylib.BankAPI.Account.CreateAccount;
import jp.pokepay.pokepaylib.BankAPI.Account.CreateAccountCpmToken;
import jp.pokepay.pokepaylib.BankAPI.Account.GetAccount;
import jp.pokepay.pokepaylib.BankAPI.Account.GetAccountTransactions;
import jp.pokepay.pokepaylib.BankAPI.Account.GetAccountBalances;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.BankAPI.Bill.CreateBill;
import jp.pokepay.pokepaylib.BankAPI.Bill.DeleteBill;
import jp.pokepay.pokepaylib.BankAPI.Bill.GetBill;
import jp.pokepay.pokepaylib.BankAPI.Bill.UpdateBill;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.CreateCashtray;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.DeleteCashtray;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.GetCashtray;
import jp.pokepay.pokepaylib.BankAPI.Cashtray.UpdateCashtray;
import jp.pokepay.pokepaylib.BankAPI.Check.CreateCheck;
import jp.pokepay.pokepaylib.BankAPI.Check.DeleteCheck;
import jp.pokepay.pokepaylib.BankAPI.Check.GetCheck;
import jp.pokepay.pokepaylib.BankAPI.Check.UpdateCheck;
import jp.pokepay.pokepaylib.BankAPI.CpmToken.GetCpmToken;
import jp.pokepay.pokepaylib.BankAPI.PrivateMoney.SearchPrivateMoneys;
import jp.pokepay.pokepaylib.BankAPI.Terminal.AddTerminalPublicKey;
import jp.pokepay.pokepaylib.BankAPI.Terminal.GetTerminal;
import jp.pokepay.pokepaylib.BankAPI.Terminal.UpdateTerminal;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CancelTransaction;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithBill;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithCpm;
import jp.pokepay.pokepaylib.BankAPI.Transaction.GetTransaction;
import jp.pokepay.pokepaylib.BankAPI.User.DeleteUserEmail;
import jp.pokepay.pokepaylib.BankAPI.User.GetUserAccounts;
import jp.pokepay.pokepaylib.BankAPI.User.GetUserTransactions;
import jp.pokepay.pokepaylib.BankAPI.User.RegisterUserEmail;
import jp.pokepay.pokepaylib.BankAPI.User.SendConfirmationEmail;
import jp.pokepay.pokepaylib.Env;
import jp.pokepay.pokepaylib.Pokepay;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Responses.Account;
import jp.pokepay.pokepaylib.Responses.AccountCpmToken;
import jp.pokepay.pokepaylib.Responses.Bill;
import jp.pokepay.pokepaylib.Responses.Cashtray;
import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.Responses.NoContent;
import jp.pokepay.pokepaylib.Responses.PaginatedAccountBalances;
import jp.pokepay.pokepaylib.Responses.PaginatedAccounts;
import jp.pokepay.pokepaylib.Responses.PaginatedPrivateMoneys;
import jp.pokepay.pokepaylib.Responses.PaginatedTransactions;
import jp.pokepay.pokepaylib.Responses.ServerKey;
import jp.pokepay.pokepaylib.Responses.Terminal;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class LowLevelAPITests {
    private String merchantAccessToken = "7mL_asUSVHUZhW11nDJzlm-Xa7-01VjgVBPi8Hd43UAqYpMCEfEuzLPGWfKr0VU9";// 購入店を想定
    private String customerAccessToken = "oNTvWHFqv512JJQhUVgAwCx7LphHVpHFAp_jDMQ62THIN9iOwNfUXA9nMkI66xoA";// 購入客を想定(残高あり)

    public LowLevelAPITests() {
        Pokepay.setEnv(Env.DEVELOPMENT);
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
        CreateAccount createAccount = new CreateAccount("accountTest", "216d1e39-3acb-454e-9fbf-74c33c5bfd5d");
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
        CreateBill createBill = new CreateBill(1, "bill test", null);
        Bill bill = createBill.send(merchantAccessToken);
        // Billの確認 //
        GetBill getBill = new GetBill(bill.id);
        bill = getBill.send(merchantAccessToken);
        // Billの支払いを2円に変更 //
        UpdateBill updateBill = new UpdateBill(bill.id, 2, "bill update");
        bill = updateBill.send(merchantAccessToken);
        // Billの消去 //
        DeleteBill deleteBill = new DeleteBill(bill.id);
        NoContent nc = deleteBill.send(merchantAccessToken);
        // 消去できているかの確認 //
        getBill = new GetBill(bill.id);
        bill = getBill.send(merchantAccessToken);
        return "OK";
    }

    public String CashtrayTest() throws BankRequestError, ProcessingError {
        // Cashtrayの作成 //
        CreateCashtray createCashtray = new CreateCashtray(1, "cashtray test", -1);
        Cashtray cashtray = createCashtray.send(customerAccessToken);
        System.out.println("cashtray created " + cashtray.id);
        // Cashtrayの確認 //
        GetCashtray getCashtray = new GetCashtray(cashtray.id);
        cashtray = getCashtray.send(customerAccessToken);
        System.out.println("cashtray got " + cashtray.id);
        // Cashtrayの支払いを2円に変更 //
        UpdateCashtray updateCashtray = new UpdateCashtray(cashtray.id, 2, "cashtray update", -1);
        cashtray = updateCashtray.send(customerAccessToken);
        System.out.println("cashtray updated " + cashtray.id);
        // Cashtrayの消去 //
        DeleteCashtray deleteCashtray = new DeleteCashtray(cashtray.id);
        NoContent nc = deleteCashtray.send(customerAccessToken);
        System.out.println("cashtray deleted " + cashtray.id);
        // 消去できているかの確認 //
        getCashtray = new GetCashtray(cashtray.id);
        try {
            cashtray = getCashtray.send(customerAccessToken);
            // If it success its wrong.
            throw new ProcessingError("cashtray delete failed " + cashtray.toString());
        } catch (BankRequestError e) {
            if (e.statusCode == 404) {
                System.out.println("cashtray NotFound " + cashtray.id);
                return "OK";
            }
            throw e;
        }

    }

    public String CheckTest() throws BankRequestError, ProcessingError {
        // Checkの作成 //
        CreateCheck createCheck = new CreateCheck(1, "check test", null);
        Check check = createCheck.send(merchantAccessToken);
        System.out.println("check created " + check.id);
        // Checkの確認 //
        GetCheck getCheck = new GetCheck(check.id);
        check = getCheck.send(merchantAccessToken);
        if (check.is_disabled == true) {
            throw new ProcessingError("Disabled check");
        }
        System.out.println("check got " + check.id);
        // Checkの支払いを2円に変更 //
        UpdateCheck updateCheck = new UpdateCheck(check.id, 2, "check update");
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
        SearchPrivateMoneys searchPrivateMoneys = new SearchPrivateMoneys(null, true, null, null, 30);
        PaginatedPrivateMoneys paginatedPrivateMoney = searchPrivateMoneys.send(customerAccessToken);
        // フィルタあり（部分一致）で確認 //
        searchPrivateMoneys = new SearchPrivateMoneys("コイン", true, null, null, 30);
        paginatedPrivateMoney = searchPrivateMoneys.send(customerAccessToken);
        System.out.println("PrivateMoney:\n" + paginatedPrivateMoney.toString());
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
        // FIXME!
        UserTransaction userTransaction = null;
        String ret = null;
        // TerminalInfoの取得 //
        GetTerminal getTerminal = new GetTerminal();
        Terminal terminal = getTerminal.send(customerAccessToken);
        System.out.println("terminal info got " + terminal.toString());
        // Billの作成 //
        CreateBill createBill = new CreateBill(1, "transaction test", null);
        Bill bill = createBill.send(merchantAccessToken);
        System.out.println("bill created " + bill.toString());
        // 上記のBillで取引を作成 //
        CreateTransactionWithBill createTransactionWithBill = new CreateTransactionWithBill(bill.id, null, 1);
        userTransaction = createTransactionWithBill.send(customerAccessToken);
        System.out.println("transaction created " + userTransaction.toString());
        // 取引の確認 //
        GetTransaction getTransaction = new GetTransaction(userTransaction.id);
        userTransaction = getTransaction.send(customerAccessToken);
        System.out.println("transaction got " + userTransaction.toString());
        // 取引のキャンセル
        final CancelTransaction cancelTransaction = new CancelTransaction(userTransaction.id);
        NoContent nc = cancelTransaction.send(merchantAccessToken);
        System.out.println("transaction canceled " + userTransaction.toString());
        // キャンセルできたか確認 //
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
            GetCpmToken getCpmToken = new GetCpmToken("000011112222");
            getCpmToken.send(customerAccessToken);
            throw new ProcessingError("this call should be fail");
        } catch (BankRequestError e) {
            if (e.statusCode != 404) {
                throw e;
            }
        }

        System.out.println("客のアクセストークンでCpmをcreate");
        CreateAccount createAccount = new CreateAccount("accountTest", "216d1e39-3acb-454e-9fbf-74c33c5bfd5d");
        Account account = createAccount.send(customerAccessToken);
        CreateAccountCpmToken createAccountCpmToken = new CreateAccountCpmToken(account.id, CreateAccountCpmToken.SCOPE_BOTH, 100, "data");
        AccountCpmToken cpmToken = createAccountCpmToken.send(customerAccessToken);

        System.out.println("客のアクセストークンでCpmをget");
        GetCpmToken getCpmToken = new GetCpmToken(cpmToken.cpm_token);
        cpmToken = getCpmToken.send(customerAccessToken);
        if (cpmToken.transaction != null) {
            throw new ProcessingError("transaction should be null.");
        }
        SystemClock.sleep(50);
        System.out.println("客のアクセストークンでCpmをcreate");
        CreateAccountCpmToken createAccountCpmToken2 = new CreateAccountCpmToken(account.id, CreateAccountCpmToken.SCOPE_BOTH, 100, "data");
        AccountCpmToken cpmToken2 = createAccountCpmToken2.send(customerAccessToken);
        System.out.printf(cpmToken2.toString());

        SystemClock.sleep(50);
        System.out.println("客のアクセストークンでCpmをget、前のCpmがinvalidなことを確認");
        try {
            cpmToken = getCpmToken.send(customerAccessToken);
            // throw new ProcessingError("this call should be fail " + getCpmToken.cpmToken);
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
        CreateTransactionWithCpm createTransactionWithCpm = new CreateTransactionWithCpm(cpmToken2.cpm_token, null, 100);
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
        CreateAccountCpmToken createAccountCpmToken3 = new CreateAccountCpmToken(account.id, CreateAccountCpmToken.SCOPE_BOTH, 100, "data");
        AccountCpmToken cpmToken3 = createAccountCpmToken3.send(customerAccessToken);

        System.out.println("店のアクセストークンでCpmにpayment");
        SystemClock.sleep(50);
        CreateTransactionWithCpm createTransactionWithCpm2 = new CreateTransactionWithCpm(cpmToken3.cpm_token, null, -100);
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

}
