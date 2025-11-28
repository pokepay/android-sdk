package jp.pokepay.pokepaylib;

public enum Env {
    DEVELOPMENT("-dev"),
    SANDBOX("-sandbox"),
    PRODUCTION(""),
    QA("-qa"),
    TOPIC1("-topic1"),
    TOPIC2("-topic2"),
    TOPIC3("-topic3"),
    LOADTEST("-loadtest");

    private final String key;

    private Env(String s) {
        key = s;
    }

    public String WWW_BASE_URL() {
        return "https://www" + key + ".pokepay.jp";
    }
    public String API_BASE_URL() { return "https://api" + key + ".pokepay.jp"; }

    private static Env inst = null;

    public static Env current() {
        if (inst == null) {
            inst = DEVELOPMENT;
        }
        return inst;
    }

    public static Env setCurrent(Env e) {
        inst = e;
        return inst;
    }
};
