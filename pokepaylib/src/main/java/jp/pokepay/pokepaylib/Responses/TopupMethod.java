package jp.pokepay.pokepaylib.Responses;

import jp.pokepay.pokepaylib.Response;

public class TopupMethod extends Response {
    public static final String TYPE_CREDIT_CARD = "credit-card";

    public String type;
    public String name;
    public double[] amounts;
    public double[] range;

    public boolean isCreditCard() {
        return TYPE_CREDIT_CARD.equals(type);
    }
}
