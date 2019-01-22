package jp.pokepay.pokepay;


import jp.pokepay.pokepaylib.BankAPI.Terminal.GetTerminal;
import jp.pokepay.pokepaylib.MessagingAPI.GetUnreadCount;
import jp.pokepay.pokepaylib.MessagingAPI.ListMessages;
import jp.pokepay.pokepaylib.MessagingAPI.SendMessage;
import jp.pokepay.pokepaylib.Responses.Message;
import jp.pokepay.pokepaylib.Responses.MessageUnreadCount;
import jp.pokepay.pokepaylib.Responses.PaginatedMessages;
import jp.pokepay.pokepaylib.Responses.Terminal;

public class MessagingAPITests {
    private String accessToken1 = "7mL_asUSVHUZhW11nDJzlm-Xa7-01VjgVBPi8Hd43UAqYpMCEfEuzLPGWfKr0VU9";// ユーザ１　購入店を想定
    private String accessToken2 = "oNTvWHFqv512JJQhUVgAwCx7LphHVpHFAp_jDMQ62THIN9iOwNfUXA9nMkI66xoA";// ユーザ２　購入客を想定(残高あり)

    public MessagingAPITests(){

    }

    public boolean AllTests(){
        
        // ユーザ２からユーザ１へメッセージを送ります
        
        // ユーザ１のterminalを取得　（user id 取得のため） //
        GetTerminal getTerminal = new GetTerminal();
        Terminal terminal = getTerminal.procSend(accessToken1);
        if(terminal == null){
            return false;
        }

        // メッセージを送る fromユーザ２ toユーザ１ //
        SendMessage sendMessage = new SendMessage(terminal.user.id, -2, "messageing tests", "body", null, null);
        Message message = sendMessage.procSend(accessToken2);
        if(message == null){
            return false;
        }

        // 未読数を取得
        GetUnreadCount getUnreadCount = new GetUnreadCount();
        MessageUnreadCount messageUnreadCount = getUnreadCount.procSend(accessToken1);
        if(messageUnreadCount == null){
            return false;
        }

        // メッセージを取得
        ListMessages listMessages = new ListMessages(null, null, 30);
        PaginatedMessages paginatedMessages = listMessages.procSend(accessToken1);
        if(paginatedMessages == null){
            return false;
        }

        // ToDo:ReceiveMessageAttachmentのテストは、仕様がわからないため保留


        return true;
    }
}
