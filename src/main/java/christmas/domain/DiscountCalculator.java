package christmas.domain;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public class DiscountCalculator {
    private static final int CHRISTMAS_DISCOUNT_START = -1000;
    private static final int DAILY_INCREMENT = -100;
    private static final int WEEKDAY_DESSERT_DISCOUNT = -2023;
    private static final int WEEKEND_MAIN_DISH_DISCOUNT = -2023;
    private static final int SPECIAL_DISCOUNT = -1000;
    private final List<Integer> specialDiscountDays = Arrays.asList(3, 10, 17, 24, 25, 31);
    private static final Menu EVENT_GIFT = Menu.CHAMPAGNE;
    private static final int EVENT_GIFT_QUANTITY = 1;
    private static final int GIFT_THRESHOLD_AMOUNT = 120000;

    public DiscountDetails calculateDiscount(Order order, LocalDate date) {
        DiscountDetails discountDetails = new DiscountDetails();
        addChristmasDiscount(discountDetails, date);
        addWeekdayDessertDiscount(discountDetails, order, date);
        addWeekendMainDishDiscount(discountDetails, order, date);
        addSpecialDiscount(discountDetails, date);
        addGiftEvent(discountDetails, order);
        return discountDetails;
    }

    private void addGiftEvent(DiscountDetails discountDetails, Order order) {
        if(order.calculateTotalAmount() > GIFT_THRESHOLD_AMOUNT) {
            order.addGift(EVENT_GIFT, EVENT_GIFT_QUANTITY);
            discountDetails.addDiscount("증정 이벤트", EVENT_GIFT.getPrice() * -1);
        }
    }

    private void addChristmasDiscount(DiscountDetails discountDetails, LocalDate date) {
        if (date.getMonthValue() == 12 && date.getDayOfMonth() <= 25) {
            int christmasDiscount = CHRISTMAS_DISCOUNT_START + (date.getDayOfMonth() - 1) * DAILY_INCREMENT;
            discountDetails.addDiscount("크리스마스 디데이 할인", christmasDiscount);
        }
    }

    private void addWeekdayDessertDiscount(DiscountDetails discountDetails, Order order, LocalDate date) {
        if (isWeekday(date) && order.containsCategory(Menu.Category.DESSERT)) {
            int dessertDiscount = order.countItemsByCategory(Menu.Category.DESSERT) * WEEKDAY_DESSERT_DISCOUNT;
            discountDetails.addDiscount("평일 디저트 할인", dessertDiscount);
        }
    }

    private void addWeekendMainDishDiscount(DiscountDetails discountDetails, Order order, LocalDate date) {
        if (isWeekend(date) && order.containsCategory(Menu.Category.MAIN)) {
            int mainDishDiscount = order.countItemsByCategory(Menu.Category.MAIN) * WEEKEND_MAIN_DISH_DISCOUNT;
            discountDetails.addDiscount("주말 메인 요리 할인", mainDishDiscount);
        }
    }

    private void addSpecialDiscount(DiscountDetails discountDetails, LocalDate date) {
        if (specialDiscountDays.contains(date.getDayOfMonth())) {
            discountDetails.addDiscount("특별 할인", SPECIAL_DISCOUNT);
        }
    }

    private boolean isWeekday(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day != DayOfWeek.FRIDAY && day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.FRIDAY || day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }
}

