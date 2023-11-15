package christmas.controller;

import christmas.domain.*;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;
    private final DiscountCalculator discountCalculator;
    private final BadgeAwarder badgeAwarder;

    public EventController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.discountCalculator = new DiscountCalculator();
        this.badgeAwarder = new BadgeAwarder();
    }

    public void run() {
        outputView.printGreeting();
        try {
            LocalDate date = inputView.readDate();
            Order order = takeOrder();
            DiscountDetails discountDetails = discountCalculator.calculateDiscount(order, date);
            int finalAmount = order.calculateFinalAmount(discountDetails.getTotalDiscount());
            String badge = Badge.awardBadge(discountDetails.getTotalDiscount());
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            run(); // 재귀 호출로 에러 발생 시 다시 시작
        }
    }

    private Order takeOrder() {
        String orderedMenu = inputView.readMenu();
        Order order = new Order();

        // 입력 문자열 파싱
        String[] items = orderedMenu.split(",");
        for (String item : items) {
            String[] parts = item.split("-");
            if (parts.length != 2) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요");
            }

            String itemName = parts[0].trim();

            Menu menu = Menu.fromString(itemName);

            int quantity;
            try {
                quantity = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            if (quantity <= 0) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }

            order.addItem(menu, quantity);
        }
        return order;
    }
}

