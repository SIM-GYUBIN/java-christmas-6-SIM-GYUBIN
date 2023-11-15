package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import java.time.DateTimeException;
import java.time.LocalDate;

public class InputView {
    public LocalDate readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        try {
            int date = Integer.parseInt(Console.readLine());
            return LocalDate.of(2023, 12, date);
        } catch (NumberFormatException | DateTimeException e) {
            System.out.println("[ERROR] 유효하지 않은 입력입니다. 다시 입력해 주세요.");
            return readDate();
        }
    }

    public String readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String input = Console.readLine();
        return input;
    }
}