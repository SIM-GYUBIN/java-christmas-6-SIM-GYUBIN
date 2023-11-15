package christmas.controller;

import christmas.domain.*;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.time.LocalDate;

public class EventController {
    private final InputView inputView;
    private final OutputView outputView;
    private final DiscountCalculator discountCalculator;
    private final OrderService orderService;

    public EventController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.discountCalculator = new DiscountCalculator();
        this.orderService = new OrderService();
    }

    public void run() {
        outputView.printGreeting();
        LocalDate date = inputView.readDate();
        Order order = processOrder();
        processDiscountsAndPrintDetails(date, order);
    }

    private Order processOrder() {
        Order order = new Order();
        try {
            String orderedMenu = inputView.readMenu();
            order = orderService.createOrderFromInput(orderedMenu);

        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            processOrder();
        }
        return order;
    }

    private void processDiscountsAndPrintDetails(LocalDate date, Order order) {
        DiscountDetails discountDetails = discountCalculator.calculateDiscount(order, date);
        int finalAmount = order.calculateFinalAmount(discountDetails.getTotalDiscount());
        String badge = Badge.awardBadge(discountDetails.getTotalDiscount());
        outputView.printOrderDetails(date, order, discountDetails, finalAmount, badge);
    }
}