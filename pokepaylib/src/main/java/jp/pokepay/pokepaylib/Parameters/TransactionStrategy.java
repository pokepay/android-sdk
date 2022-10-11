package jp.pokepay.pokepaylib.Parameters;

public enum TransactionStrategy {
    POINT_PREFERRED("point_preferred"),
    MONEY_ONLY("money_only");

    private final String strategy;

    TransactionStrategy(String strategy) {
        this.strategy = strategy;
    }

    public String getStrategy() {
        return strategy;
    }
}
