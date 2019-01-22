package jp.pokepay.pokepaylib.Responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Transaction {

    public class Cancel {
        String id;

        public Cancel(String id) {
            this.id = id;
        }

        public String path(){
            return "/transactions/" + id;
        }
    }

    public class CreatewithBill {
        public String billId;
        public String accountId;
        public double amount;

        public CreatewithBill(String billId, String accountId, double amount){
            this.billId    = billId;
            this.accountId = accountId;
            this.amount    = amount;
        }

        public String path(){
            return "/transactions";
        }
    }

    public class CreateWithCashtray {
        public String cashtrayId;
        public String accountId;

        public CreateWithCashtray(String cashtrayId, String accountId){
            this.cashtrayId = cashtrayId;
            this.accountId  = accountId;
        }

        public String path(){
            return "/transactions";
        }
    }

    public class CreateWithCheck {
        public String checkId;
        public String accountId;

        public CreateWithCheck(String checkId, String accountId) {
            this.checkId   = checkId;
            this.accountId = accountId;
        }

        public String path(){
            return "/transactions";
        }
    }

    public class Get {
        public String id;

        public Get(String id){
            this.id = id;
        }

        public String path(){
            return "/transactions/" + id;
        }
    }

    public class SendToAccount {
        public String accountId;
        public double amount;
        public String receiverTerminalId;
        public String senderAccountId;
        public String description;

        public SendToAccount(String accountId, double amount, String receiverTerminalId, String senderAccountId, String description){
            this.accountId          = accountId;
            this.amount             = amount;
            this.receiverTerminalId = receiverTerminalId;
            this.senderAccountId    = senderAccountId;
            this.description        = description;
        }

        public String path(){
            return "/accounts/" + accountId + "/transactions";
        }
    }

    public class SendToUser {
        public String userId;
        public double amount;
        public String receiverTerminalId;
        public String senderAccountId;
        public String description;

        public SendToUser(String userId, double amount, String receiverTerminalId, String senderAccountId, String description){
            this.userId             = userId;
            this.amount             = amount;
            this.receiverTerminalId = receiverTerminalId;
            this.senderAccountId    = senderAccountId;
            this.description        = description;
        }

        public String path(){
            return "/users/" + userId + "/transactions";
        }
    }

}
