package com.generic.retailer;

import java.math.BigDecimal;

public interface Discount {

    BigDecimal getDiscountAmount(TrolleyItem trolleyItem);
}
