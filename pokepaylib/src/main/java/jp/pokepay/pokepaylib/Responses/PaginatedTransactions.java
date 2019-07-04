package jp.pokepay.pokepaylib.Responses;

import jp.pokepay.pokepaylib.Response;

public class PaginatedTransactions extends Response {
    public int             per_page;
    public int             count;
    public String          next;
    public String          prev;
    public UserTransaction items[];
}
