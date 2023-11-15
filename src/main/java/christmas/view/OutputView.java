package christmas.view;

import christmas.domain.DiscountDetails;
import christmas.domain.Order;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OutputView {
    private final String GREETING_MESSAGE = "안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.";
    private final String EVENT_PREVIEW_FORMAT = "%s에 우테코 식당에서 받을 이벤트 혜택 미리 보기!";
    private final String ORDER_MENU_HEADER = "\n<주문 메뉴>";
    private final String TOTAL_COST_BEFORE_DISCOUNT_HEADER = "\n<할인 전 총주문 금액>";
    private final String GIFT_MENU_HEADER = "\n<증정 메뉴>";
    private final String BENEFITS_DETAILS_HEADER = "\n<혜택 내역>";
    private final String TOTAL_BENEFITS_AMOUNT_HEADER = "\n<총혜택 금액>";
    private final String EXPECTED_PAYMENT_AFTER_DISCOUNT_HEADER = "\n<할인 후 예상 결제 금액>";
    private final String DECEMBER_EVENT_BADGE_HEADER = "\n<12월 이벤트 배지>";
    private final String UNIT_QUANTITY = "개";
    private final String UNIT_CURRENCY = "원";
    private final String TEXT_NONE = "없음";

    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

    private void printEventPreview(LocalDate date) {
        String dateString = date.format(DateTimeFormatter.ofPattern("M월 d일"));
        String eventPreviewMessage = String.format(EVENT_PREVIEW_FORMAT, dateString);
        System.out.println(eventPreviewMessage);
    }

    public void printGreeting() {
        System.out.println(GREETING_MESSAGE);
    }

    public void printOrderDetails(LocalDate date, Order order, DiscountDetails discountDetails, int finalAmount, String badge) {
        printEventPreview(date);
        printMenu(order);
        printCostBeforeDiscount(order);
        printGift(order);
        printBenefitsDetails(discountDetails);
        printTotalDiscounts(discountDetails);
        printFinalAmount(finalAmount);
        printBadge(badge);
    }

    public void printMenu(Order order) {
        System.out.println(ORDER_MENU_HEADER);

        order.getItems().forEach((menu, quantity) -> {
            System.out.println(menu.getName() + " " + quantity + UNIT_QUANTITY);
        });
    }

    private void printCostBeforeDiscount(Order order) {
        System.out.println(TOTAL_COST_BEFORE_DISCOUNT_HEADER);
        System.out.println(formatAmount(order.calculateTotalAmount()) + UNIT_CURRENCY);
    }

    private void printGift(Order order) {
        System.out.println(GIFT_MENU_HEADER);

        if (order.getGifts().isEmpty()) {
            System.out.println(TEXT_NONE);
            return;
        }

        order.getGifts().forEach((menu, quantity) -> {
            System.out.println(menu.getName() + " " + quantity + UNIT_QUANTITY);
        });
    }

    private void printBenefitsDetails(DiscountDetails discountDetails) {
        System.out.println(BENEFITS_DETAILS_HEADER);

        if (discountDetails.getDiscountDetails().isEmpty()) {
            System.out.println(TEXT_NONE);
            return;
        }

        discountDetails.getDiscountDetails().forEach((discountType, amount) -> {
            System.out.println(discountType + ": " + formatAmount(amount) + UNIT_CURRENCY);
        });
    }

    private void printTotalDiscounts(DiscountDetails discountDetails) {
        System.out.println(TOTAL_BENEFITS_AMOUNT_HEADER);
        System.out.println(formatAmount(discountDetails.getTotalDiscount()) + UNIT_CURRENCY);
    }

    private void printFinalAmount(int finalAmount) {
        System.out.println(EXPECTED_PAYMENT_AFTER_DISCOUNT_HEADER);
        System.out.println(formatAmount(finalAmount) + UNIT_CURRENCY);
    }

    private void printBadge(String badge) {
        System.out.println(DECEMBER_EVENT_BADGE_HEADER);

        if (badge.isEmpty()) {
            System.out.println(TEXT_NONE);
            return;
        }
        System.out.println(badge);
    }

    private String formatAmount(int amount) {
        return NumberFormat.getNumberInstance(Locale.KOREA).format(amount);
    }
}
