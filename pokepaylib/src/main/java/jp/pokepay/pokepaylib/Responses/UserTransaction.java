package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class UserTransaction {
    public String  id;
    public User    user;
    public double  balance;
    public double  amount;
    public double  money_amount;
    public double  point_amount;
    public Account account;
    public String  description;
    public String  done_at;
    public String  type;
    public boolean is_modified;

    public UserTransaction(){
        user = new User();
        account = new Account();
    }

    public void print(){
        System.out.println("UserTransaction(");
        System.out.println("id: \"" + id + "\",");
        System.out.print("user: ");
        user.print();
        System.out.println("balance: \"" + balance + "\",");
        System.out.println("amount: \"" + amount + "\",");
        System.out.println("moneyAmount: \"" + money_amount + "\",");
        System.out.println("pointAmount: \"" + point_amount + "\",");
        System.out.print("account: ");
        account.print();
        System.out.println("description: \"" + description + "\",");
        System.out.println("doneAt: \"" + done_at + "\",");
        System.out.println("),");
    }
}
