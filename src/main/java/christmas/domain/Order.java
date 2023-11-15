package christmas.domain;

import java.util.HashMap;
import java.util.Map;

import christmas.domain.Menu;

public class Order {
    private Map<Menu, Integer> items;
    private Map<Menu, Integer> gifts;

    public Order() {
        this.items = new HashMap<>();
        this.gifts = new HashMap<>();
    }

    public void addItem(Menu item, int quantity) {
        items.put(item, items.getOrDefault(item, 0) + quantity);
    }
    public void addGift(Menu item, int quantity) {
        gifts.put(item, gifts.getOrDefault(item, 0) + quantity);
    }

    public boolean containsCategory(Menu.Category category) {
        return items.keySet().stream().anyMatch(item -> item.getCategory() == category);
    }

    public int countItemsByCategory(Menu.Category category) {
        return items.entrySet().stream()
                .filter(entry -> entry.getKey().getCategory() == category)
                .mapToInt(Map.Entry::getValue)
                .sum();
    }

    public int calculateTotalAmount() {
        return items.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
    public int calculateGiftAmount() {
        return gifts.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }

    public int calculateFinalAmount(int totalDiscount) {
        int totalAmount = calculateTotalAmount() + calculateGiftAmount();
        int finalAmount = totalAmount + totalDiscount;

        return Math.max(finalAmount, 0);
    }

    public Map<Menu, Integer> getItems() {
        return new HashMap<>(items);
    }
    public Map<Menu, Integer> getGifts() {
        return new HashMap<>(gifts);
    }
}


