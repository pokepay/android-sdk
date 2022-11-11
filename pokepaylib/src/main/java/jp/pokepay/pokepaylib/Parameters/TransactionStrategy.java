package jp.pokepay.pokepaylib.Parameters;

public enum TransactionStrategy {
    POINT_PREFERRED("point-preferred"),
    MONEY_ONLY("money-only");

    private final String strategy;

    TransactionStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getStrategy() {
        return strategy;
    }
}
