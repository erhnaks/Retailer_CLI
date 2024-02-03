package com.generic.retailer;

import java.math.BigDecimal;

public interface Product {
    ProductType getType();
    BigDecimal getPrice();
}
