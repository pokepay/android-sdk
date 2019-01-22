package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PaginatedMessages {
    public int per_page;
    public int count;
    public String next;
    public String prev;
    public Message items[];

    public PaginatedMessages(){
        this.items = new Message[2];
    }

    public void print(){
        System.out.println("PaginatedMessages(");
        System.out.println("perPage: \"" + per_page + "\",");
        System.out.println("count: \"" + count + "\",");
        System.out.println("next: \"" + next + "\",");
        System.out.println("prev: \"" + prev + "\",");
        for(int i=0;i<items.length;i++) {
            System.out.print("Message: ");
            items[i].print();
        }
        System.out.println("),");
    }
}
