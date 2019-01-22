package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Message {
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


    public void print(){
        System.out.println("PrivateMoney(");
        System.out.println("id: \"" + id + "\",");
        System.out.println("subject: \"" + subject + "\",");
        System.out.println("body: \"" + body + "\",");
        System.out.print("fromUser: ");
        from_user.print();
        System.out.print("toUser: ");
        to_user.print();
        System.out.println("isUnread: \"" + is_unread + "\",");
        System.out.print("attachment: ");
        attachment.print();
        System.out.println("),");
    }
}
