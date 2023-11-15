package christmas.constants;

public enum DiscountType {
    CHRISTMAS("크리스마스 디데이 할인", -1000, -100),
    WEEKDAY_DESSERT("평일 디저트 할인", -2023),
    WEEKEND_MAIN_DISH("주말 메인 요리 할인", -2023),
    SPECIAL("특별 할인", -1000),
    GIFT_EVENT("증정 이벤트");

    private final String name;
    private final int discountAmount;
    private final int increment;

    DiscountType(String name, int discountAmount, int increment) {
        this.name = name;
        this.discountAmount = discountAmount;
        this.increment = increment;
    }

    DiscountType(String name, int discountAmount) {
        this.name = name;
        this.discountAmount = discountAmount;
        this.increment = 0;
    }

    DiscountType(String name) {
        this.name = name;
        this.discountAmount = 0;
        this.increment = 0;
    }


    public String getName() {
        return name;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public int getIncrement() {
        return increment;
    }
}


