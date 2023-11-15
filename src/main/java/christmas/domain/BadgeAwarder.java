package christmas.domain;

public class BadgeAwarder {
    public String awardBadge(DiscountDetails discountDetails) {
        int totalDiscount = discountDetails.getTotalDiscount();

        if(totalDiscount >= 5000)
            return "별";
        if(totalDiscount >= 10000)
            return "트리";
        if(totalDiscount >= 20000)
            return "산타";

        return null;
    }
}
