package christmas.domain;

import christmas.domain.Menu;
import christmas.domain.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
    }

    @Test
    void 주문항목_추가_정상작동() {
        order.addItem(Menu.CAESAR_SALAD, 2);
        order.addItem(Menu.BBQ_RIBS, 1);

        assertThat(order.getItems()).containsOnly(
                entry(Menu.CAESAR_SALAD, 2),
                entry(Menu.BBQ_RIBS, 1)
        );
    }

    @Test
    void 사은품_추가_정상작동() {
        order.addGift(Menu.CHAMPAGNE, 1);

        assertThat(order.getGifts()).containsOnly(
                entry(Menu.CHAMPAGNE, 1)
        );
    }

    @Test
    void 특정_카테고리_항목_존재여부_정상_확인() {
        order.addItem(Menu.CAESAR_SALAD, 1);

        assertThat(order.containsCategory(Menu.Category.APPETIZER)).isTrue();
        assertThat(order.containsCategory(Menu.Category.MAIN)).isFalse();
    }

    @Test
    void 특정_카테고리_항목_개수_계산_확인() {
        order.addItem(Menu.CAESAR_SALAD, 2);
        order.addItem(Menu.T_BONE_STEAK, 1);

        assertThat(order.countItemsByCategory(Menu.Category.APPETIZER)).isEqualTo(2);
        assertThat(order.countItemsByCategory(Menu.Category.MAIN)).isEqualTo(1);
    }

    @Test
    void 총_주문_금액_정확하게_계산() {
        order.addItem(Menu.CAESAR_SALAD, 2); // 8000 * 2
        order.addItem(Menu.BBQ_RIBS, 1); // 54000

        assertThat(order.calculateTotalAmount()).isEqualTo(70000);
    }

    @Test
    void 총_증정_금액_정확하게_계산() {
        order.addGift(Menu.CHAMPAGNE, 1); // 25000

        assertThat(order.calculateGiftAmount()).isEqualTo(25000);
    }

    @Test
    void 최종_금액_정확하게_계산() {
        order.addItem(Menu.CAESAR_SALAD, 2); // 8000 * 2
        order.addGift(Menu.CHAMPAGNE, 1); // 25000
        int discount = -5000; // Discount amount

        assertThat(order.calculateFinalAmount(discount)).isEqualTo(36000);
    }
}
