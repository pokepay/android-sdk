package jp.pokepay.pokepaylib.BankAPI.Bill;

import jp.pokepay.pokepaylib.Responses.Bill;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.BankAPI.BankRequest;

public class UpdateBill extends BankRequest {
    public String id;
    public double amount;
    public String description;

    public UpdateBill(String id, double amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
    }

    protected final String path() {
        return "/bills/" + id;
    }

    protected final Request.Method method() {
        return Request.Method.PATCH;
    }

    protected final String body() {
        boolean flag = false;
        String str = "{";
        if(amount >= 0) {
            str += "\"amount\":\"" + (int) amount;
            flag = true;
        }
        if(description != null) {
            str += "\", \"description\":\"" + description;
            flag = true;
        }

        if(flag) {
            str += "\"}";
        }
        else{
            str += "}";
        }
        return str;
    }

    public final Bill send(String accessToken) {
        return super.send(Bill.class, accessToken);
    }
}
