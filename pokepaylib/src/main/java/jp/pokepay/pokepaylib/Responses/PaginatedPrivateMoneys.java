package jp.pokepay.pokepaylib.Responses;

import jp.pokepay.pokepaylib.Response;

public class PaginatedPrivateMoneys extends Response {
    public int     per_page;
    public int     count;
    public String  next;
    public String  prev;
    public PrivateMoney items[];
}
