package jp.pokepay.pokepaylib.Responses;

import java.util.Date;

import jp.pokepay.pokepaylib.Response;

public class MessageAttachment extends Response {
    public Double money_amount;
    public Double point_amount;
    public PrivateMoney private_money;
    public boolean is_received;
    public Date expires_at;
}
