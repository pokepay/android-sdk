package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import java.util.Date;
import jp.pokepay.pokepaylib.Response;

public class Message extends Response {
    @NonNull
    public String id;
    @NonNull
    public String subject;
    @NonNull
    public String body;
    @NonNull
    public User from_user;
    @NonNull
    public User to_user;
    @NonNull
    public User send_by;
    @NonNull
    public User send_to;
    @NonNull
    public Date sent_at;
    @NonNull
    public boolean is_unread;
    @NonNull
    public MessageAttachment attachment;
}
