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
    void addItem_ShouldAddItemsCorrectly() {
        order.addItem(Menu.CAESAR_SALAD, 2);
        order.addItem(Menu.BBQ_RIBS, 1);

        assertThat(order.getItems()).containsOnly(
                entry(Menu.CAESAR_SALAD, 2),
                entry(Menu.BBQ_RIBS, 1)
        );
    }

    @Test
    void addGift_ShouldAddGiftsCorrectly() {
        order.addGift(Menu.CHAMPAGNE, 1);

        assertThat(order.getGifts()).containsOnly(
                entry(Menu.CHAMPAGNE, 1)
        );
    }

    @Test
    void containsCategory_ShouldReturnTrueIfItemsOfCategoryExist() {
        order.addItem(Menu.CAESAR_SALAD, 1);

        assertThat(order.containsCategory(Menu.Category.APPETIZER)).isTrue();
        assertThat(order.containsCategory(Menu.Category.MAIN)).isFalse();
    }

    @Test
    void countItemsByCategory_ShouldReturnCorrectCount() {
        order.addItem(Menu.CAESAR_SALAD, 2);
        order.addItem(Menu.T_BONE_STEAK, 1);

        assertThat(order.countItemsByCategory(Menu.Category.APPETIZER)).isEqualTo(2);
        assertThat(order.countItemsByCategory(Menu.Category.MAIN)).isEqualTo(1);
    }

    @Test
    void calculateTotalAmount_ShouldReturnCorrectAmount() {
        order.addItem(Menu.CAESAR_SALAD, 2); // 8000 * 2
        order.addItem(Menu.BBQ_RIBS, 1); // 54000

        assertThat(order.calculateTotalAmount()).isEqualTo(70000);
    }

    @Test
    void calculateGiftAmount_ShouldReturnCorrectAmountForGifts() {
        order.addGift(Menu.CHAMPAGNE, 1); // 25000

        assertThat(order.calculateGiftAmount()).isEqualTo(25000);
    }

    @Test
    void calculateFinalAmount_ShouldReturnCorrectFinalAmount() {
        order.addItem(Menu.CAESAR_SALAD, 2); // 8000 * 2
        order.addGift(Menu.CHAMPAGNE, 1); // 25000
        int discount = -5000; // Discount amount

        assertThat(order.calculateFinalAmount(discount)).isEqualTo(36000);
    }
}
