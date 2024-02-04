package com.generic.retailer;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class ThursdayDiscount implements Discount {

    private final LocalDate date;

    private static final BigDecimal THURSDAY_DISCOUNT_PERCENTAGE = BigDecimal.valueOf(20);

    public ThursdayDiscount(LocalDate date) {
        this.date = date;
    }

    @Override
    public BigDecimal getDiscountAmount(TrolleyItem trolleyItem) {
        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal total = trolleyItem.getTotal();
        if (trolleyItem.getProduct().getType() == ProductType.DVD)
            if(trolleyItem.getQuantity() % 2 == 0) {
                return BigDecimal.ZERO;
            } else {
                total = trolleyItem.getProduct().getPrice();
            }
        if (this.date.getDayOfWeek() == DayOfWeek.THURSDAY) {
            discount = total.multiply(THURSDAY_DISCOUNT_PERCENTAGE).divide(BigDecimal.valueOf(100L));
        }
        return discount;
    }
}