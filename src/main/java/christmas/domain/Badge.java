package christmas.domain;

public enum Badge {
    SANTA("산타", -20000),
    TREE("트리", -10000),
    STAR("별", -5000);

    private final String name;
    private final int discountThreshold;

    Badge(String name, int discountThreshold) {
        this.name = name;
        this.discountThreshold = discountThreshold;
    }

    public String getName() {
        return name;
    }

    public int getDiscountThreshold() {
        return discountThreshold;
    }

    public static String awardBadge(int totalDiscount) {
        for (Badge badge : Badge.values()) {
            if (totalDiscount <= badge.getDiscountThreshold()) {
                return badge.getName();
            }
        }
        return "";
    }
}
