package com.generic.retailer;

import java.math.BigDecimal;

public class TwoForOneDiscount implements Discount {

    @Override
    public BigDecimal getDiscountAmount(TrolleyItem trolleyItem) {
        BigDecimal discount = BigDecimal.ZERO;
        if (trolleyItem.getProduct().getType() == ProductType.DVD) {
            discount = trolleyItem.getProduct().getPrice().multiply(BigDecimal.valueOf((Math.floor(trolleyItem.getQuantity() / 2))));
        }
        return discount;
    }
}