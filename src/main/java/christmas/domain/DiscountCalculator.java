package christmas.domain;

import christmas.constants.DiscountType;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.List;

public class DiscountCalculator {
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
        if (order.calculateTotalAmount() > GIFT_THRESHOLD_AMOUNT) {
            order.addGift(EVENT_GIFT, EVENT_GIFT_QUANTITY);
            discountDetails.addDiscount(DiscountType.GIFT_EVENT.getName(), EVENT_GIFT.getPrice() * -1);
        }
    }

    private void addChristmasDiscount(DiscountDetails discountDetails, LocalDate date) {
        if (date.getMonthValue() == 12 && date.getDayOfMonth() <= 25) {
            String discountName = DiscountType.CHRISTMAS.getName();
            int christmasDiscount = DiscountType.CHRISTMAS.getDiscountAmount() + (date.getDayOfMonth() - 1) * DiscountType.CHRISTMAS.getIncrement();
            discountDetails.addDiscount(discountName, christmasDiscount);
        }
    }

    private void addWeekdayDessertDiscount(DiscountDetails discountDetails, Order order, LocalDate date) {
        if (isWeekday(date) && order.containsCategory(Menu.Category.DESSERT)) {
            String discountName = DiscountType.WEEKDAY_DESSERT.getName();
            int dessertDiscount = order.countItemsByCategory(Menu.Category.DESSERT) * DiscountType.WEEKDAY_DESSERT.getDiscountAmount();
            discountDetails.addDiscount(discountName, dessertDiscount);
        }
    }

    private void addWeekendMainDishDiscount(DiscountDetails discountDetails, Order order, LocalDate date) {
        if (isWeekend(date) && order.containsCategory(Menu.Category.MAIN)) {
            String discountName = DiscountType.WEEKEND_MAIN_DISH.getName();
            int mainDishDiscount = order.countItemsByCategory(Menu.Category.MAIN) * DiscountType.WEEKEND_MAIN_DISH.getDiscountAmount();
            discountDetails.addDiscount(discountName, mainDishDiscount);
        }
    }

    private void addSpecialDiscount(DiscountDetails discountDetails, LocalDate date) {
        if (specialDiscountDays.contains(date.getDayOfMonth())) {
            String discountName = DiscountType.SPECIAL.getName();
            int mainSpecialDiscount = DiscountType.SPECIAL.getDiscountAmount();
            discountDetails.addDiscount(discountName, mainSpecialDiscount);
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

