package jp.pokepay.pokepaylib.Responses;

import android.support.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class SevenElevenAtmSession extends Response {
   @NonNull
   public String           qr_info;
   @NonNull
   public double           amount;
   @NonNull
   public Account          account;
   public UserTransaction  transaction;
}
