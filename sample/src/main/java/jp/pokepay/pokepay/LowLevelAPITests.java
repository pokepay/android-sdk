package jp.pokepay.pokepay;


import jp.pokepay.pokepaylib.BankAPI.Account.CreateAccount;
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
import jp.pokepay.pokepaylib.BankAPI.PrivateMoney.SearchPrivateMoneys;
import jp.pokepay.pokepaylib.BankAPI.Terminal.AddTerminalPublicKey;
import jp.pokepay.pokepaylib.BankAPI.Terminal.GetTerminal;
import jp.pokepay.pokepaylib.BankAPI.Terminal.UpdateTerminal;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CancelTransaction;
import jp.pokepay.pokepaylib.BankAPI.Transaction.CreateTransactionWithBill;
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
    private String accessToken1 = "7mL_asUSVHUZhW11nDJzlm-Xa7-01VjgVBPi8Hd43UAqYpMCEfEuzLPGWfKr0VU9";// 購入店を想定
    private String accessToken2 = "oNTvWHFqv512JJQhUVgAwCx7LphHVpHFAp_jDMQ62THIN9iOwNfUXA9nMkI66xoA";// 購入客を想定(残高あり)

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
        Account account = createAccount.send(accessToken1);
        // アカウントの確認 //
        GetAccount getAccount = new GetAccount(account.id);
        account = getAccount.send(accessToken1);
        // アカウントidからTransactionの取得 //
        GetAccountTransactions getAccountTransactions = new GetAccountTransactions(account.id, null, null, 10);
        PaginatedTransactions paginatedTransactions = getAccountTransactions.send(accessToken1);
        // アカウントidからBalancesの確認 //
        GetAccountBalances getBalances = new GetAccountBalances(account.id, null, null, 10);
        PaginatedAccountBalances paginatedAccountBalances = getBalances.send(accessToken1);
        return "OK";
    }

    public String BillTest() throws BankRequestError, ProcessingError {
        // Billの作成 //
        CreateBill createBill = new CreateBill(1, "bill test", null);
        Bill bill = createBill.send(accessToken1);
        // Billの確認 //
        GetBill getBill = new GetBill(bill.id);
        bill = getBill.send(accessToken1);
        // Billの支払いを2円に変更 //
        UpdateBill updateBill = new UpdateBill(bill.id, 2, "bill update");
        bill = updateBill.send(accessToken1);
        // Billの消去 //
        DeleteBill deleteBill = new DeleteBill(bill.id);
        NoContent nc = deleteBill.send(accessToken1);
        // 消去できているかの確認 //
        getBill = new GetBill(bill.id);
        bill = getBill.send(accessToken1);
        return "OK";
    }

    public String CashtrayTest() throws BankRequestError, ProcessingError {
        // Cashtrayの作成 //
        CreateCashtray createCashtray = new CreateCashtray(1, "cashtray test", -1);
        Cashtray cashtray = createCashtray.send(accessToken2);
        // Cashtrayの確認 //
        GetCashtray getCashtray = new GetCashtray(cashtray.id);
        cashtray = getCashtray.send(accessToken2);
        // Cashtrayの支払いを2円に変更 //
        UpdateCashtray updateCashtray = new UpdateCashtray(cashtray.id, 2, "cashtray update", -1);
        cashtray = updateCashtray.send(accessToken2);
        // Cashtrayの消去 //
        DeleteCashtray deleteCashtray = new DeleteCashtray(cashtray.id);
        NoContent nc = deleteCashtray.send(accessToken2);
        // 消去できているかの確認 //
        getCashtray = new GetCashtray(cashtray.id);
        cashtray = getCashtray.send(accessToken2);
        return "OK";
    }

    public String CheckTest() throws BankRequestError, ProcessingError {
        // Checkの作成 //
        CreateCheck createCheck = new CreateCheck(1, "check test", null);
        Check check = createCheck.send(accessToken1);
        // Checkの確認 //
        GetCheck getCheck = new GetCheck(check.id);
        check = getCheck.send(accessToken1);
        // Checkの支払いを2円に変更 //
        UpdateCheck updateCheck = new UpdateCheck(check.id, 2, "check update");
        check = updateCheck.send(accessToken1);
        // Checkの消去 //
        DeleteCheck deleteCheck = new DeleteCheck(check.id);
        NoContent nc = deleteCheck.send(accessToken1);
        // 消去できているかの確認 //
        getCheck = new GetCheck(check.id);
        check = getCheck.send(accessToken1);
        return "OK";
    }

    public String PrivateMoneyTest() throws BankRequestError, ProcessingError {
        // フィルタ無しで全て確認 //
        SearchPrivateMoneys searchPrivateMoneys = new SearchPrivateMoneys(null, true, null, null, 30);
        PaginatedPrivateMoneys paginatedPrivateMoney = searchPrivateMoneys.send(accessToken2);
        // フィルタあり（部分一致）で確認 //
        searchPrivateMoneys = new SearchPrivateMoneys("コイン", true, null, null, 30);
        paginatedPrivateMoney = searchPrivateMoneys.send(accessToken2);
        return "OK";
    }


    public String TerminalTest(String pubkey) throws BankRequestError, ProcessingError {
        // TerminalInfoの取得 //
        GetTerminal getTerminal = new GetTerminal();
        Terminal terminal =  getTerminal.send(accessToken1);
        // Terminalの名前とトークンを変更 //
        UpdateTerminal updateTerminal = new UpdateTerminal(terminal.account.id , "term00", "hoge");
        terminal = updateTerminal.send(accessToken1);
        // pubkeyの追加　　　注意：pubkeyは毎回違うものを入れる必要あり //
        AddTerminalPublicKey addTerminalPublicKey = new AddTerminalPublicKey(pubkey);
        ServerKey key = addTerminalPublicKey.send(accessToken1);
        return "OK";
    }

    public String TransactionTest() throws BankRequestError, ProcessingError {
        UserTransaction userTransaction = null;
        CancelTransaction cancelTransaction = null;
        String ret = null;
        // TerminalInfoの取得 //
        GetTerminal getTerminal = new GetTerminal();
        Terminal terminal =  getTerminal.send(accessToken2);
        // Billの作成 //
        CreateBill createBill = new CreateBill(1, "transaction test", null);
        Bill bill = createBill.send(accessToken1);
        // 上記のBillで取引を作成 //
        CreateTransactionWithBill createTransactionWithBill = new CreateTransactionWithBill(bill.id, null, 1);
        userTransaction = createTransactionWithBill.send(accessToken2);
        // 取引の確認 //
        GetTransaction getTransaction = new GetTransaction(userTransaction.id);
        userTransaction = getTransaction.send(accessToken2);
        // 取引のキャンセル //
        cancelTransaction = new CancelTransaction(userTransaction.id);
        NoContent nc = cancelTransaction.send(accessToken2);
        // キャンセルできたか確認 //
        getTransaction = new GetTransaction(userTransaction.id);
        userTransaction = getTransaction.send(accessToken2);
        return "OK";
    }

    public String UserTest(String email) throws BankRequestError, ProcessingError {
        String str;
        // Terminalの取得 //
        GetTerminal getTerminal = new GetTerminal();
        Terminal terminal = getTerminal.send(accessToken2);
        // TerminalからuserIdを取得してUserの確認 //
        GetUserAccounts getUserAccounts = new GetUserAccounts(terminal.user.id,null,null,1);
        PaginatedAccounts paginatedAccounts = getUserAccounts.send(accessToken2);
        // TerminalからUserIdを取得してTransactionsの確認 //
        GetUserTransactions getUserTransactions = new GetUserTransactions(terminal.user.id,null,null,1);
        PaginatedTransactions paginatedTransactions = getUserTransactions.send(accessToken2);
        // アドレスの登録
        RegisterUserEmail registerUserEmail = new RegisterUserEmail(email);
        NoContent nc = registerUserEmail.send(accessToken2);
        // 確認メールの送信 //
        SendConfirmationEmail sendConfirmationEmail = new SendConfirmationEmail(terminal.user.id, email);
        nc = sendConfirmationEmail.send(accessToken2);
        // 登録アドレスの削除 //
        DeleteUserEmail deleteUserEmail = new DeleteUserEmail(terminal.user.id, email);
        nc = deleteUserEmail.send(accessToken2);
        // この後にメール承認必要 //
        return "OK";
    }

}
