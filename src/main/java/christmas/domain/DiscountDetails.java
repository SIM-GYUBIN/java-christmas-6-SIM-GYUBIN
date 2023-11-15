package christmas.domain;

import java.util.HashMap;
import java.util.Map;

public class DiscountDetails {
    private Map<String, Integer> discountDetails;

    public DiscountDetails() {
        this.discountDetails = new HashMap<>();
    }

    public void addDiscount(String discountType, int amount) {
        this.discountDetails.put(discountType, amount);
    }

    public int getTotalDiscount() {
        int total = 0;
        for (int amount : discountDetails.values()) {
            total += amount;
        }
        return total;
    }

    public Map<String, Integer> getDiscountDetails() {
        return new HashMap<>(discountDetails);
    }
}
