package jp.pokepay.pokepaylib.Parameters;

import android.support.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import jp.pokepay.pokepaylib.Parameter;

public class Product extends Parameter {

    @NonNull
    String jan_code;
    @NonNull
    String name;
    double unit_price;
    double price;
    boolean is_discounted;
    String other;

    public static Product create(
            String jan_code_primary, String jan_code_secondary, String name,
            double unit_price, double price, boolean is_discounted, Double amount, String amount_unit)
    {
        String jan_code = jan_code_primary;
        if (jan_code_secondary != null) {
            jan_code += "___" + jan_code_secondary;
        }
        Product product = new Product();
        product.jan_code = jan_code;
        product.name = name;
        product.unit_price = unit_price;
        product.price = price;
        product.is_discounted = is_discounted;
        try {
            JSONObject other = new JSONObject();
            if (amount != null) {
                other.put("amount", (double) amount);
            }
            if (amount_unit != null) {
                other.put("amount_unit", amount_unit);
            }
            product.other = other.toString();
        } catch (JSONException e) {
            product.other = "{}"; // shouldn't be called.
        }
        return product;
    }
}