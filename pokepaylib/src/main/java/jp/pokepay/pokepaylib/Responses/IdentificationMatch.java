package jp.pokepay.pokepaylib.Responses;

import androidx.annotation.NonNull;

import jp.pokepay.pokepaylib.Response;

public class IdentificationMatch extends Response {
    @NonNull
    public boolean name;
    @NonNull
    public boolean gender;
    @NonNull
    public boolean address;
    @NonNull
    public boolean date_of_birth;
}