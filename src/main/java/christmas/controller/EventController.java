package christmas.controller;

import christmas.domain.*;
import christmas.view.InputView;
import christmas.view.OutputView;

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

    }

}

