package jp.pokepay.pokepaylib;

public class TokenInfo {
    public static enum Type {
        CASHTRAY,
        BILL,
        CHECK,
        POKEREGI,
    };
    public Type type;
    public Object info;
    TokenInfo(Type type, Object info) {
        this.type = type;
        this.info = info;
    }
}
