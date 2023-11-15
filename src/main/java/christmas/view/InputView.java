package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.ErrorMessage;

import java.time.DateTimeException;
import java.time.LocalDate;

public class InputView {
    private static final String PROMPT_DATE = "12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)";
    private static final String PROMPT_MENU = "주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)";

    public LocalDate readDate() {
        System.out.println(PROMPT_DATE);
        try {
            int date = Integer.parseInt(Console.readLine());
            return LocalDate.of(2023, 12, date);
        } catch (NumberFormatException | DateTimeException e) {
            System.out.println(ErrorMessage.INVALID_DATE.getFormattedMessage());
            return readDate();
        }
    }

    public String readMenu() {
        System.out.println(PROMPT_MENU);
        return Console.readLine();
    }
}