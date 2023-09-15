package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class BankPay extends Response {
    @NonNull
    public String id;
    @NonNull
    public String bank_name;
    @NonNull
    public String bank_code;
    @NonNull
    public String branch_number;
    @NonNull
    public String branch_name;
    @NonNull
    public int deposit_type;
    @NonNull
    public String masked_account_number;
    @NonNull
    public String account_name;
    public String private_money_id;
}
