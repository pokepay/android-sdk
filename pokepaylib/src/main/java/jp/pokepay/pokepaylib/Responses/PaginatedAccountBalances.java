package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class PaginatedAccountBalances extends Response {
    public int     per_page;
    public int     count;
    public String  next;
    public String  prev;
    @NonNull
    public AccountBalance items[];
}
