package jp.pokepay.pokepay;


import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.BankAPI.Terminal.GetTerminal;
import jp.pokepay.pokepaylib.Env;
import jp.pokepay.pokepaylib.MessagingAPI.GetUnreadCount;
import jp.pokepay.pokepaylib.MessagingAPI.ListMessages;
import jp.pokepay.pokepaylib.MessagingAPI.SendMessage;
import jp.pokepay.pokepaylib.Pokepay;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Responses.Message;
import jp.pokepay.pokepaylib.Responses.MessageUnreadCount;
import jp.pokepay.pokepaylib.Responses.PaginatedMessages;
import jp.pokepay.pokepaylib.Responses.Terminal;

public class MessagingAPITests {
    private String accessToken1 = "7mL_asUSVHUZhW11nDJzlm-Xa7-01VjgVBPi8Hd43UAqYpMCEfEuzLPGWfKr0VU9";// ユーザ１　購入店を想定
    private String accessToken2 = "oNTvWHFqv512JJQhUVgAwCx7LphHVpHFAp_jDMQ62THIN9iOwNfUXA9nMkI66xoA";// ユーザ２　購入客を想定(残高あり)

    public MessagingAPITests(){
        Pokepay.setEnv(Env.DEVELOPMENT);
    }

    public String AllTests() {
        try {
            String res = "";
            // ユーザ２からユーザ１へメッセージを送ります
            // ユーザ１のterminalを取得　（user id 取得のため
            final GetTerminal getTerminal = new GetTerminal();
            final Terminal terminal = getTerminal.send(accessToken1);
            res += "Terminal: " + terminal.toString() + "\n";
            // メッセージを送る fromユーザ２ toユーザ１
            final SendMessage sendMessage = new SendMessage(terminal.user.id, -2, "messageing tests", "body", null, null);
            final Message message = sendMessage.send(accessToken2);
            res += "Message: " + message.toString() + "\n";
            // 未読数を取得
            final GetUnreadCount getUnreadCount = new GetUnreadCount();
            final MessageUnreadCount messageUnreadCount = getUnreadCount.send(accessToken1);
            res += "MessageUnreadCount: " + messageUnreadCount.toString() + "\n";
            // メッセージを取得
            final ListMessages listMessages = new ListMessages(null, null, 30);
            final PaginatedMessages paginatedMessages = listMessages.send(accessToken1);
            res += "PanginatedMessages: " + paginatedMessages.toString() + "\n";
            return res;
        } catch (ProcessingError e) {
            return "ProcessingError: " + e.getMessage() + "\n";
        } catch (BankRequestError e) {
            return "BankRequestError: " + e.toString() + "\n";
        }
    }
}
