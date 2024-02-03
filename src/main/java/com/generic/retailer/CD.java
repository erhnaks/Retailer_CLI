package com.generic.retailer;

import java.math.BigDecimal;

public final class CD implements Product {
    @Override
    public ProductType getType() {
        return ProductType.CD;
    }

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.TEN;
    }
}
