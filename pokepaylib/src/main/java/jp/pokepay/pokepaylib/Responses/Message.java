package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jp.pokepay.pokepaylib.Response;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Message extends Response {
    public String id;
    public String subject;
    public String body;
    public User from_user;
    public User to_user;
    public User send_by;
    public User send_to;
    public boolean is_unread;
    public MessageAttachment attachment;

    public Message(){
        from_user = new User();
        to_user   = new User();
        send_by   = new User();
        send_to   = new User();
        attachment = new MessageAttachment();
    }
}
