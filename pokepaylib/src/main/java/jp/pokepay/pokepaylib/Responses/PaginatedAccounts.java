package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PaginatedAccounts {
    public int     per_page;
    public int     count;
    public String  next;
    public String  prev;
    public Account items[];

    public PaginatedAccounts(){
        items = new Account[2];
    }

    public void print(){
        System.out.println("PaginatedAccounts(");
        System.out.println("perPage: \"" + per_page + "\",");
        System.out.println("count: \"" + count + "\",");
        System.out.println("next: \"" + next + "\",");
        System.out.println("prev: \"" + prev + "\",");
        for(int i=0;i<items.length;i++) {
            System.out.print("Account: ");
            items[i].print();
        }
        System.out.println("),");
    }
}
