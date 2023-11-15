package christmas.domain;

public class OrderService {

    public Order createOrderFromInput(String orderedMenu) {
        Order order = new Order();
        String[] items = orderedMenu.split(",");
        for (String item : items) {
            processMenuItem(order, item);
        }
        return order;
    }

    private void processMenuItem(Order order, String item) {
        String[] parts = item.split("-");
        validateItemParts(parts);

        Menu menu = getMenuFromName(parts[0]);
        int quantity = getQuantityFromParts(parts);

        order.addItem(menu, quantity);
    }

    private void validateItemParts(String[] parts) {
        if (parts.length != 2) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private Menu getMenuFromName(String itemName) {
        try {
            return Menu.fromString(itemName.trim());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private int getQuantityFromParts(String[] parts) {
        try {
            int quantity = Integer.parseInt(parts[1].trim());
            if (quantity <= 0) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            return quantity;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
