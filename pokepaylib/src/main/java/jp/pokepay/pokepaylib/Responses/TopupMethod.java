package jp.pokepay.pokepaylib.Responses;

import jp.pokepay.pokepaylib.Response;

public class TopupMethod extends Response {
    // "type" can be "credit-card", "sevenbank-atm", "paytree-bank", or "cvs"
    public String type;
    public String name;
    public double[] amounts;
    public double[] range;
}
