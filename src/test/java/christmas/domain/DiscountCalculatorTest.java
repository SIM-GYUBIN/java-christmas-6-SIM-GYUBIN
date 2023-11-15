package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

class DiscountCalculatorTest {
    private DiscountCalculator discountCalculator;
    private Order order;

    @BeforeEach
    void setUp() {
        discountCalculator = new DiscountCalculator();
        order = new Order();
    }

    @Test
    void 크리스마스_디데이_할인_확인() {
        LocalDate date = LocalDate.of(2023, 12, 25);
        order.addItem(Menu.CAESAR_SALAD, 1);
        DiscountDetails discountDetails = discountCalculator.calculateDiscount(order, date);

        assertThat(discountDetails.getDiscountDetails()).containsKey("크리스마스 디데이 할인");
        assertThat(discountDetails.getDiscountDetails().get("크리스마스 디데이 할인")).isEqualTo(-3400);
    }

    @Test
    void 평일_디저트_할인_확인() {
        LocalDate date = LocalDate.of(2023, 12, 6);
        order.addItem(Menu.CHOCOLATE_CAKE, 1); // Dessert item
        DiscountDetails discountDetails = discountCalculator.calculateDiscount(order, date);

        assertThat(discountDetails.getDiscountDetails()).containsKey("평일 디저트 할인");
        assertThat(discountDetails.getDiscountDetails().get("평일 디저트 할인")).isEqualTo(-2023);
    }

    @Test
    void 주말_메인요리_할인_확인() {
        LocalDate date = LocalDate.of(2023, 12, 10);
        order.addItem(Menu.T_BONE_STEAK, 1); // Main dish
        DiscountDetails discountDetails = discountCalculator.calculateDiscount(order, date);

        assertThat(discountDetails.getDiscountDetails()).containsKey("주말 메인 요리 할인");
        assertThat(discountDetails.getDiscountDetails().get("주말 메인 요리 할인")).isEqualTo(-2023);
    }

    @Test
    void 특별_할인_확인() {
        LocalDate date = LocalDate.of(2023, 12, 10);
        order.addItem(Menu.CAESAR_SALAD, 1);
        DiscountDetails discountDetails = discountCalculator.calculateDiscount(order, date);

        assertThat(discountDetails.getDiscountDetails()).containsKey("특별 할인");
        assertThat(discountDetails.getDiscountDetails().get("특별 할인")).isEqualTo(-1000);
    }

    @Test
    void 증정_이벤트_확인() {
        LocalDate date = LocalDate.of(2023, 12, 15);
        order.addItem(Menu.T_BONE_STEAK, 3);
        DiscountDetails discountDetails = discountCalculator.calculateDiscount(order, date);

        assertThat(discountDetails.getDiscountDetails()).containsKey("증정 이벤트");
        assertThat(order.getGifts()).containsKey(Menu.CHAMPAGNE);
    }
}
