package jp.pokepay.pokepay;


import jp.pokepay.pokepaylib.BankAPI.Account.CreateAccount;
import jp.pokepay.pokepaylib.BankAPI.Account.GetAccount;
import jp.pokepay.pokepaylib.BankAPI.Account.GetAccountTransactions;
import jp.pokepay.pokepaylib.BankAPI.Account.GetBalances;
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
import jp.pokepay.pokepaylib.BankAPI.PrivateMoney.SearchPrivateMoney;
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
import jp.pokepay.pokepaylib.Responses.Account;
import jp.pokepay.pokepaylib.Responses.Bill;
import jp.pokepay.pokepaylib.Responses.Cashtray;
import jp.pokepay.pokepaylib.Responses.Check;
import jp.pokepay.pokepaylib.Responses.PaginatedAccountBalances;
import jp.pokepay.pokepaylib.Responses.PaginatedAccounts;
import jp.pokepay.pokepaylib.Responses.PaginatedPrivateMoney;
import jp.pokepay.pokepaylib.Responses.PaginatedTransactions;
import jp.pokepay.pokepaylib.Responses.ServerKey;
import jp.pokepay.pokepaylib.Responses.Terminal;
import jp.pokepay.pokepaylib.Responses.UserTransaction;

public class LowLevelAPITests {
    private String accessToken1 = "7mL_asUSVHUZhW11nDJzlm-Xa7-01VjgVBPi8Hd43UAqYpMCEfEuzLPGWfKr0VU9";// 購入店を想定
    private String accessToken2 = "oNTvWHFqv512JJQhUVgAwCx7LphHVpHFAp_jDMQ62THIN9iOwNfUXA9nMkI66xoA";// 購入客を想定(残高あり)

    public LowLevelAPITests(){

    }

    // 全てのテストを一括で行う //
    public void AllTests(){
        System.out.println("AccoutTest:" + AccountTest());
        System.out.println("BillTest:" + BillTest());
        System.out.println("CashtrayTest:" + CashtrayTest());
        System.out.println("CheckTest:" + CheckTest());
        System.out.println("PrivateMoneyTest:" + PrivateMoneyTest());
        System.out.println("TerminalTest:" + TerminalTest("pubkey"));
        System.out.println("TransactionTest:" + TransactionTest());
        System.out.println("UserTest:" + UserTest("xxxxxxxxx@xxxx.xxx"));
    }


    public boolean AccountTest(){
        // privateMoneyIdでアカウントの作成 //
        CreateAccount createAccount = new CreateAccount("accountTest", "216d1e39-3acb-454e-9fbf-74c33c5bfd5d");
        Account account = createAccount.procSend(accessToken1);
        if(account == null){
            return false;
        }
        // アカウントの確認 //
        GetAccount getAccount = new GetAccount(account.id);
        account = getAccount.procSend(accessToken1);
        if(account == null){
            return false;
        }
        // アカウントidからTransactionの取得 //
        GetAccountTransactions getAccountTransactions = new GetAccountTransactions(account.id, null, null, 10);
        PaginatedTransactions paginatedTransactions =  getAccountTransactions.procSend(accessToken1);
        if(paginatedTransactions == null){
            return false;
        }
        // アカウントidからBalancesの確認 //
        GetBalances getBalances = new GetBalances(account.id, null, null, 10);
        PaginatedAccountBalances paginatedAccountBalances =  getBalances.procSend(accessToken1);
        if(paginatedAccountBalances == null){
            return false;
        }
        return true;
    }

    public boolean BillTest(){
        // Billの作成 //
        CreateBill createBill = new CreateBill(1, "bill test", null);
        Bill bill = createBill.procSend(accessToken1);
        if(bill == null){
            return false;
        }
        // Billの確認 //
        GetBill getBill = new GetBill(bill.id);
        bill = getBill.procSend(accessToken1);
        if(bill == null){
            return false;
        }
        // Billの支払いを2円に変更 //
        UpdateBill updateBill = new UpdateBill(bill.id, 2, "bill update");
        bill = updateBill.procSend(accessToken1);
        if(bill == null){
            return false;
        }
        // Billの消去 //
        DeleteBill deleteBill = new DeleteBill(bill.id);
        String str = deleteBill.procSend(accessToken1);
        if(str == null){
            return false;
        }
        // 消去できているかの確認 //
        getBill = new GetBill(bill.id);
        bill = getBill.procSend(accessToken1);
        if(bill == null){
            return true;
        }
        return false;
    }

    public boolean CashtrayTest(){
        // Cashtrayの作成 //
        CreateCashtray createCashtray = new CreateCashtray(1, "cashtray test", -1);
        Cashtray cashtray = createCashtray.procSend(accessToken2);
        if(cashtray == null){
            return false;
        }
        // Cashtrayの確認 //
        GetCashtray getCashtray = new GetCashtray(cashtray.id);
        cashtray = getCashtray.procSend(accessToken2);
        if(cashtray == null){
            return false;
        }
        // Cashtrayの支払いを2円に変更 //
        UpdateCashtray updateCashtray = new UpdateCashtray(cashtray.id, 2, "cashtray update", -1);
        cashtray = updateCashtray.procSend(accessToken2);
        if(cashtray == null){
            return false;
        }
        // Cashtrayの消去 //
        DeleteCashtray deleteCashtray = new DeleteCashtray(cashtray.id);
        String str = deleteCashtray.procSend(accessToken2);
        if(str == null){
            return false;
        }

        // 消去できているかの確認 //
        getCashtray = new GetCashtray(cashtray.id);
        cashtray = getCashtray.procSend(accessToken2);
        if(cashtray == null){
            return true;
        }
        return false;
    }

    public boolean CheckTest(){
        // Checkの作成 //
        CreateCheck createCheck = new CreateCheck(1, "check test", null);
        Check check = createCheck.procSend(accessToken1);
        if(check == null){
            return false;
        }
        // Checkの確認 //
        GetCheck getCheck = new GetCheck(check.id);
        check = getCheck.procSend(accessToken1);
        if(check == null){
            return false;
        }
        // Checkの支払いを2円に変更 //
        UpdateCheck updateCheck = new UpdateCheck(check.id, 2, "check update");
        check = updateCheck.procSend(accessToken1);
        if(check == null){
            return false;
        }
        // Checkの消去 //
        DeleteCheck deleteCheck = new DeleteCheck(check.id);
        String ret = deleteCheck.procSend(accessToken1);
        if(ret == null) {
            return false;
        }

        // 消去できているかの確認 //
        getCheck = new GetCheck(check.id);
        check = getCheck.procSend(accessToken1);
        if(check == null){
            return true;
        }
        return false;
    }

    public boolean PrivateMoneyTest(){
        // フィルタ無しで全て確認 //
        SearchPrivateMoney searchPrivateMoney = new SearchPrivateMoney(null, true, null, null, 30);
        PaginatedPrivateMoney paginatedPrivateMoney = searchPrivateMoney.procSend(accessToken2);
        if(paginatedPrivateMoney == null){
            return false;
        }
        // フィルタあり（部分一致）で確認 //
        searchPrivateMoney = new SearchPrivateMoney("竈コイ", true, null, null, 30);
        paginatedPrivateMoney = searchPrivateMoney.procSend(accessToken2);
        if(paginatedPrivateMoney == null){
            return false;
        }
        return true;
    }


    public boolean TerminalTest(String pubkey){
        // TerminalInfoの取得 //
        GetTerminal getTerminal = new GetTerminal();
        Terminal terminal =  getTerminal.procSend(accessToken1);
        if(terminal == null){
            return false;
        }
        // Terminalの名前とトークンを変更 //
        UpdateTerminal updateTerminal = new UpdateTerminal(terminal.account.id , "term00", "hoge");
        terminal = updateTerminal.procSend(accessToken1);
        if(terminal == null){
            return false;
        }
        // pubkeyの追加　　　注意：pubkeyは毎回違うものを入れる必要あり //
        AddTerminalPublicKey addTerminalPublicKey = new AddTerminalPublicKey(pubkey);
        ServerKey key = addTerminalPublicKey.procSend(accessToken1);
        if(key == null){
            return false;
        }

        return true;
    }

    public boolean TransactionTest(){
        UserTransaction userTransaction = null;
        CancelTransaction cancelTransaction = null;
        String ret = null;

        // TerminalInfoの取得 //
        GetTerminal getTerminal = new GetTerminal();
        Terminal terminal =  getTerminal.procSend(accessToken2);
        if(terminal == null){
            return false;
        }

        // Billの作成 //
        CreateBill createBill = new CreateBill(1, "transaction test", null);
        Bill bill = createBill.procSend(accessToken1);
        if(bill == null){
            return false;
        }
        // 上記のBillで取引を作成 //
        CreateTransactionWithBill createTransactionWithBill = new CreateTransactionWithBill(bill.id, null, 1);
        userTransaction = createTransactionWithBill.procSend(accessToken2);
        if(userTransaction == null) {
            return false;
        }
        // 取引の確認 //
        GetTransaction getTransaction = new GetTransaction(userTransaction.id);
        userTransaction = getTransaction.procSend(accessToken2);
        if(userTransaction == null){
            return false;
        }
        // 取引のキャンセル //
        cancelTransaction = new CancelTransaction(userTransaction.id);
        ret = cancelTransaction.procSend(accessToken2);
        if(!ret.equals("204")){
            return false;
        }
        // キャンセルできたか確認 //
        getTransaction = new GetTransaction(userTransaction.id);
        userTransaction = getTransaction.procSend(accessToken2);
        if(userTransaction != null){
            return false;
        }
        return true;
    }

    public boolean UserTest(String email){
        String str;
        // Terminalの取得 //
        GetTerminal getTerminal = new GetTerminal();
        Terminal terminal = getTerminal.procSend(accessToken2);
        if(terminal == null){
            return false;
        }
        // TerminalからuserIdを取得してUserの確認 //
        GetUserAccounts getUserAccounts = new GetUserAccounts(terminal.user.id,null,null,1);
        PaginatedAccounts paginatedAccounts = getUserAccounts.procSend(accessToken2);
        if(paginatedAccounts == null){
            return false;
        }
        // TerminalからUserIdを取得してTransactionsの確認 //
        GetUserTransactions getUserTransactions = new GetUserTransactions(terminal.user.id,null,null,1);
        PaginatedTransactions paginatedTransactions = getUserTransactions.procSend(accessToken2);
        if(paginatedTransactions == null){
            return false;
        }
        // アドレスの登録
        RegisterUserEmail registerUserEmail = new RegisterUserEmail(email);
        str = registerUserEmail.procSend(accessToken2);
        if(!str.equals("500")){
            return false;
        }
        // 確認メールの送信 //
        SendConfirmationEmail sendConfirmationEmail = new SendConfirmationEmail(terminal.user.id, email);
        str = sendConfirmationEmail.procSend(accessToken2);
        if(!str.equals("204")){
            return false;
        }

        // 登録アドレスの削除 //
        DeleteUserEmail deleteUserEmail = new DeleteUserEmail(terminal.user.id, email);
        str = deleteUserEmail.procSend(accessToken2);
        if(!str.equals("204")) {
            return false;
        }
        // この後にメール承認必要 //
        return true;
    }

}
