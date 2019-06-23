package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PaginatedTransactions {
    public int             per_page;
    public int             count;
    public String          next;
    public String          prev;
    public UserTransaction items[];

    public PaginatedTransactions(){
        items = new UserTransaction[2];
    }

    public void print(){
        System.out.println("PaginatedTransactions(");
        System.out.println("perPage: \"" + per_page + "\",");
        System.out.println("count: \"" + count + "\",");
        System.out.println("next: \"" + next + "\",");
        System.out.println("prev: \"" + prev + "\",");
        for(int i=0;i<items.length;i++) {
            System.out.print("UserTransaction: ");
            items[i].print();
        }
        System.out.println("),");
    }

}