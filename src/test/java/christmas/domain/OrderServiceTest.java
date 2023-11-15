package christmas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceTest {
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
    }

    @Test
    void createOrderFromInput_ShouldCreateCorrectOrder() {
        String input = "시저샐러드-2,바비큐립-1";
        Order order = orderService.createOrderFromInput(input);

        assertThat(order.getItems()).containsOnly(
                entry(Menu.CAESAR_SALAD, 2),
                entry(Menu.BBQ_RIBS, 1)
        );
    }

    @Test
    void createOrderFromInput_ShouldThrowExceptionForInvalidMenu() {
        String input = "INVALID_MENU-1";

        assertThatThrownBy(() -> orderService.createOrderFromInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void createOrderFromInput_ShouldThrowExceptionForInvalidQuantity() {
        String input = "CAESAR_SALAD-0";

        assertThatThrownBy(() -> orderService.createOrderFromInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void createOrderFromInput_ShouldThrowExceptionForInvalidFormat() {
        String input = "CAESAR_SALAD";

        assertThatThrownBy(() -> orderService.createOrderFromInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}
