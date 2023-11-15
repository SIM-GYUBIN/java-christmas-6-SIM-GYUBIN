package christmas.domain;

import christmas.constants.ErrorMessage;

public class OrderService {
    private static final int MAX_ORDER_QUANTITY = 20;

    public Order createOrderFromInput(String orderedMenu) {
        Order order = new Order();
        String[] items = orderedMenu.split(",");
        int totalQuantity = 0;

        for (String item : items) {
            totalQuantity += processMenuItem(order, item);
            if (totalQuantity > MAX_ORDER_QUANTITY) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getFormattedMessage());
            }
        }
        return order;
    }

    private int processMenuItem(Order order, String item) {
        String[] parts = item.split("-");
        validateItemParts(parts);

        Menu menu = getMenuFromName(parts[0]);
        int quantity = getQuantityFromParts(parts);

        order.addItem(menu, quantity);
        return quantity;
    }

    private void validateItemParts(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getFormattedMessage());
        }
    }

    private Menu getMenuFromName(String itemName) {
        try {
            return Menu.fromString(itemName.trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getFormattedMessage());
        }
    }

    private int getQuantityFromParts(String[] parts) {
        try {
            int quantity = Integer.parseInt(parts[1].trim());
            if (quantity <= 0) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getFormattedMessage());
            }
            return quantity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getFormattedMessage());
        }
    }
}
