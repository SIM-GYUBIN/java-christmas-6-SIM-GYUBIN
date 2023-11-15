package christmas.view;

import camp.nextstep.edu.missionutils.Console;

import java.time.LocalDate;

public class InputView {
    public LocalDate readDate() {
        try {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            int date = Integer.parseInt(Console.readLine());

            return LocalDate.of(2023,12, date);
        } catch (IllegalArgumentException e) {
            return readDate();
        }
    }

}