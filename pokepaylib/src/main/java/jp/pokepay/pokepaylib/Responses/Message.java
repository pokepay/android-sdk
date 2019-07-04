package jp.pokepay.pokepaylib.Responses;

import java.util.Date;
import jp.pokepay.pokepaylib.Response;

public class Message extends Response {
    public String id;
    public String subject;
    public String body;
    public User from_user;
    public User to_user;
    public User send_by;
    public User send_to;
    public Date sent_at;
    public boolean is_unread;
    public MessageAttachment attachment;
}
