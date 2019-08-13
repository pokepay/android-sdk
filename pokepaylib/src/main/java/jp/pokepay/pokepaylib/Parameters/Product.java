package jp.pokepay.pokepaylib.Parameters;

import org.json.JSONException;
import org.json.JSONObject;

public class Product extends JSONObject {

    public Product(String jan_code, String name, double unit_price, double price, boolean is_discounted, JSONObject other) throws JSONException {
        this.put("jan_code", jan_code);
        this.put("name", name);
        this.put("unit_price", unit_price);
        this.put("price", price);
        this.put("is_discounted", is_discounted);
        this.put("other", other);
    }

    public static Product create(
            String jan_code_primary, String jan_code_secondary, String name,
            double unit_price, double price, boolean is_discounted, Double amount, String amount_unit) throws JSONException
    {
        JSONObject other = new JSONObject();
        if (amount != null) {
            other.put("amount", (double)amount);
        }
        if (amount_unit != null) {
            other.put("amount_unit", amount_unit);
        }
        String jan_code = "";
        if (jan_code_primary != null) {
            jan_code = jan_code_primary;
        }
        if (jan_code_secondary != null) {
            if (jan_code == "") {
                jan_code = jan_code_secondary;
            } else {
                jan_code += ("___" + jan_code_secondary);
            }
        }
        return new Product(jan_code, name, unit_price, price, is_discounted, other);
    }
}