package christmas.controller;

import christmas.domain.*;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;
    private final DiscountCalculator discountCalculator;

    public EventController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.discountCalculator = new DiscountCalculator();
    }

    public void run() {
        outputView.printGreeting();
        processOrder();
    }

    private void processOrder() {
        try {
            LocalDate date = inputView.readDate();
            Order order = takeOrder();
            processDiscountsAndPrintDetails(date, order);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            processOrder();
        }
    }

    private void processDiscountsAndPrintDetails(LocalDate date, Order order) {
        DiscountDetails discountDetails = discountCalculator.calculateDiscount(order, date);
        int finalAmount = order.calculateFinalAmount(discountDetails.getTotalDiscount());
        String badge = Badge.awardBadge(discountDetails.getTotalDiscount());
        outputView.printOrderDetails(date, order, discountDetails, finalAmount, badge);
    }

    private Order takeOrder() {
        try {
            return createOrderFromInput();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return takeOrder();
        }
    }

    private Order createOrderFromInput() {
        String orderedMenu = inputView.readMenu();
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