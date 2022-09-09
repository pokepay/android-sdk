package jp.pokepay.pokepaylib.BankAPI.Account;

import android.support.annotation.NonNull;

import jp.pokepay.pokepaylib.BankAPI.BankRequest;
import jp.pokepay.pokepaylib.BankAPI.BankRequestError;
import jp.pokepay.pokepaylib.ProcessingError;
import jp.pokepay.pokepaylib.Request;
import jp.pokepay.pokepaylib.Responses.SevenElevenAtmSession;

public class GetAccountSevenElevenAtmSession extends BankRequest {
    @NonNull
    public String qrInfo;

    public GetAccountSevenElevenAtmSession(@NonNull String qrInfo) {
        this.qrInfo = qrInfo;
    }

    protected final String path() {
        return "/seven-bank-atm-sessions/" + qrInfo;
    }

    protected final Request.Method method() {
        return Request.Method.GET;
    }

    public final SevenElevenAtmSession send(String accessToken) throws ProcessingError, BankRequestError {
        return super.send(SevenElevenAtmSession.class, accessToken);
    }
}

/*
API Test Logs (Dev)

$ curl -X GET -H "Content-Type: application/json" -H "Authorization: Bearer 8DZN-jCfqOTwKALYjWa2lYj7we8GYGTGi2UzT4cVERISl6qBIuNurDk4TMdqIUEU" https://api-dev.pokepay.jp/seven-bank-atm-sessions/91234567890123456789021 | jq .

{
  "account" : {
    "balance" : 167110.0,
    "id" : "fe2f16b9-a0f5-4b74-b322-57c6e6981606",
    "is_suspended" : false,
    "money_balance" : 167100.0,
    "name" : "",
    "nearest_expires_at" : null,
    "point_balance" : 10.0,
    "private_money" : {
      "account_image" : "https://pocketbank-assets.s3.amazonaws.com/development/private-money-account-image/coil_logo-860e776dd3514287ac606db990e031ff3f82348e.png",
      "can_use_credit_card" : true,
      "commercial_act_url" : "/private-moneys/4b138a4c-8944-4f98-a5c4-96d3c1c415eb/commercial-act",
      "custom_domain_name" : null,
      "description" : "コイル市場で使えるマネーです",
      "expiration_type" : "static",
      "id" : "4b138a4c-8944-4f98-a5c4-96d3c1c415eb",
      "images" : {
        "card" : "https://pocketbank-assets.s3.amazonaws.com/development/private-money-account-image/coil_logo-860e776dd3514287ac606db990e031ff3f82348e.png",
        "300x300" : "https://pocketbank-assets.s3.amazonaws.com/development/private-money-image/coilinc_300x300-c5604d713a1a56f7109fd69188d7b58bfa301e56.png",
        "600x600" : "https://pocketbank-assets.s3.amazonaws.com/development/private-money-image/coilinc_600x600-38ec4d2b705fcb7e6e8fab67e1b49203ee763aaa.png"
      },
      "is_exclusive" : false,
      "max_balance" : 300000.0,
      "name" : "コイルマネー",
      "oneline_message" : "コイルのマネーです！🍣",
      "organization" : {
        "code" : "coilinc",
        "name" : "コイル"
      },
      "payment_act_url" : "/private-moneys/4b138a4c-8944-4f98-a5c4-96d3c1c415eb/payment-act",
      "privacy_policy_url" : "/private-moneys/4b138a4c-8944-4f98-a5c4-96d3c1c415eb/privacy-policy",
      "terms_url" : "/private-moneys/4b138a4c-8944-4f98-a5c4-96d3c1c415eb/terms",
      "transfer_limit" : 10000.0,
      "type" : "third-party",
      "unit" : "マネー"
    }
  },
  "amount" : 1000.0,
  "qr_info" : "91234567890123456789021",
  "transaction" : null
}

------

$ curl -X GET -H "Content-Type: application/json" -H "Authorization: Bearer 8DZN-jCfqOTwKALYjWa2lYj7we8GYGTGi2UzT4cVERISl6qBIuNurDk4TMdqIUEU" https://api-dev.pokepay.jp/seven-bank-atm-sessions/91234567820123456789021 | jq .
{
  "account" : {
    "balance" : 167110.0,
    "id" : "fe2f16b9-a0f5-4b74-b322-57c6e6981606",
    "is_suspended" : false,
    "money_balance" : 167100.0,
    "name" : "",
    "nearest_expires_at" : null,
    "point_balance" : 10.0,
    "private_money" : {
      "account_image" : "https://pocketbank-assets.s3.amazonaws.com/development/private-money-account-image/coil_logo-860e776dd3514287ac606db990e031ff3f82348e.png",
      "can_use_credit_card" : true,
      "commercial_act_url" : "/private-moneys/4b138a4c-8944-4f98-a5c4-96d3c1c415eb/commercial-act",
      "custom_domain_name" : null,
      "description" : "コイル市場で使えるマネーです",
      "expiration_type" : "static",
      "id" : "4b138a4c-8944-4f98-a5c4-96d3c1c415eb",
      "images" : {
        "card" : "https://pocketbank-assets.s3.amazonaws.com/development/private-money-account-image/coil_logo-860e776dd3514287ac606db990e031ff3f82348e.png",
        "300x300" : "https://pocketbank-assets.s3.amazonaws.com/development/private-money-image/coilinc_300x300-c5604d713a1a56f7109fd69188d7b58bfa301e56.png",
        "600x600" : "https://pocketbank-assets.s3.amazonaws.com/development/private-money-image/coilinc_600x600-38ec4d2b705fcb7e6e8fab67e1b49203ee763aaa.png"
      },
      "is_exclusive" : false,
      "max_balance" : 300000.0,
      "name" : "コイルマネー",
      "oneline_message" : "コイルのマネーです！🍣",
      "organization" : {
        "code" : "coilinc",
        "name" : "コイル"
      },
      "payment_act_url" : "/private-moneys/4b138a4c-8944-4f98-a5c4-96d3c1c415eb/payment-act",
      "privacy_policy_url" : "/private-moneys/4b138a4c-8944-4f98-a5c4-96d3c1c415eb/privacy-policy",
      "terms_url" : "/private-moneys/4b138a4c-8944-4f98-a5c4-96d3c1c415eb/terms",
      "transfer_limit" : 10000.0,
      "type" : "third-party",
      "unit" : "マネー"
    }
  },
  "amount" : 1000.0,
  "qr_info" : "91234567820123456789021",
  "transaction" : {
    "account" : {
      "balance" : 167110.0,
      "id" : "fe2f16b9-a0f5-4b74-b322-57c6e6981606",
      "is_suspended" : false,
      "money_balance" : 167100.0,
      "name" : "",
      "nearest_expires_at" : null,
      "point_balance" : 10.0,
      "private_money" : {
        "account_image" : "https://pocketbank-assets.s3.amazonaws.com/development/private-money-account-image/coil_logo-860e776dd3514287ac606db990e031ff3f82348e.png",
        "can_use_credit_card" : true,
        "commercial_act_url" : "/private-moneys/4b138a4c-8944-4f98-a5c4-96d3c1c415eb/commercial-act",
        "custom_domain_name" : null,
        "description" : "コイル市場で使えるマネーです",
        "expiration_type" : "static",
        "id" : "4b138a4c-8944-4f98-a5c4-96d3c1c415eb",
        "images" : {
          "card" : "https://pocketbank-assets.s3.amazonaws.com/development/private-money-account-image/coil_logo-860e776dd3514287ac606db990e031ff3f82348e.png",
          "300x300" : "https://pocketbank-assets.s3.amazonaws.com/development/private-money-image/coilinc_300x300-c5604d713a1a56f7109fd69188d7b58bfa301e56.png",
          "600x600" : "https://pocketbank-assets.s3.amazonaws.com/development/private-money-image/coilinc_600x600-38ec4d2b705fcb7e6e8fab67e1b49203ee763aaa.png"
        },
        "is_exclusive" : false,
        "max_balance" : 300000.0,
        "name" : "コイルマネー",
        "oneline_message" : "コイルのマネーです！🍣",
        "organization" : {
          "code" : "coilinc",
          "name" : "コイル"
        },
        "payment_act_url" : "/private-moneys/4b138a4c-8944-4f98-a5c4-96d3c1c415eb/payment-act",
        "privacy_policy_url" : "/private-moneys/4b138a4c-8944-4f98-a5c4-96d3c1c415eb/privacy-policy",
        "terms_url" : "/private-moneys/4b138a4c-8944-4f98-a5c4-96d3c1c415eb/terms",
        "transfer_limit" : 10000.0,
        "type" : "third-party",
        "unit" : "マネー"
      }
    },
    "amount" : 1000.0,
    "balance" : 167110.0,
    "customer_balance" : null,
2022-09-09 16:55:45.566 7299-7322/jp.pokepay.pokepay D/SDK Test:     "description" : "seven atm topup!",
    "done_at" : "2022-09-07T07:44:58.358585Z",
    "id" : "19bafab8-151d-45f0-afce-5358956ca05a",
    "is_modified" : false,
    "money_amount" : 1000.0,
    "point_amount" : 0.0,
    "transfers" : [ ],
    "type" : "topup",
    "user" : {
      "id" : "55694dbd-582a-442f-80e1-a23e0f49b3cd",
      "is_merchant" : true,
      "name" : "Pokepay"
    }
  }
}
 */