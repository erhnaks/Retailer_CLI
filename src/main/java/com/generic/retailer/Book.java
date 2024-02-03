package com.generic.retailer;

import java.math.BigDecimal;

public final class Book implements Product {

    @Override
    public ProductType getType() {
        return ProductType.BOOK;
    }

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.valueOf(5L);
    }
}
