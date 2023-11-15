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
    void 입력으로부터_정확한_주문_생성() {
        String input = "시저샐러드-2,바비큐립-1";
        Order order = orderService.createOrderFromInput(input);

        assertThat(order.getItems()).containsOnly(
                entry(Menu.CAESAR_SALAD, 2),
                entry(Menu.BBQ_RIBS, 1)
        );
    }

    @Test
    void 메뉴판에_없는_메뉴_입력시_예외_발생() {
        String input = "신라면-1";

        assertThatThrownBy(() -> orderService.createOrderFromInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 주문_메뉴_개수가_20개_초과시_예외_발생() {
        String input = "시저샐러드-9,바비큐립-12";

        assertThatThrownBy(() -> orderService.createOrderFromInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
    @Test
    void 주문_메뉴_중복시_예외_발생() {
        String input = "시저샐러드-1,바비큐립-1,시저샐러드-1";

        assertThatThrownBy(() -> orderService.createOrderFromInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 수량이_0_또는_음수일_경우_예외_발생() {
        String input = "시저샐러드-0";

        assertThatThrownBy(() -> orderService.createOrderFromInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 잘못된_형식의_주문_입력시_예외_발생() {
        String input = "시저샐러드";

        assertThatThrownBy(() -> orderService.createOrderFromInput(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}

