package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PaginatedAccountBalances {
    public int     per_page;
    public int     count;
    public String  next;
    public String  prev;
    public AccountBalance items[];
    public PaginatedAccountBalances(){
        items = new AccountBalance[2];
    }

    public void print(){
        System.out.println("PaginatedAccountBalances(");
        System.out.println("perPage: \"" + per_page + "\",");
        System.out.println("count: \"" + count + "\",");
        System.out.println("next: \"" + next + "\",");
        System.out.println("prev: \"" + prev + "\",");
        for(int i=0;i<items.length;i++) {
            System.out.print("AccountBalance: ");
            items[i].print();
        }
        System.out.println("),");
    }
}
