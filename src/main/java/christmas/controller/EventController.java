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
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            run(); // 재귀 호출로 에러 발생 시 다시 시작
        }
    }

}

