package jp.pokepay.pokepaylib.BankAPI.autogen.responses;

import jp.pokepay.pokepaylib.Response;

public class AccountTopupQuota extends Response {
    public Integer id;
    public Integer amount;
    public String description;
    public String event_name;
    public Boolean is_splittable;
    public Integer used_amount;
    public String status;
    public String starts_at;
    public String ends_at;
    public String ends_at_buffer;
}
