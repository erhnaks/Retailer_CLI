package com.generic.retailer;

import java.math.BigDecimal;

public class DVD implements Product {
    @Override
    public ProductType getType() {
        return ProductType.DVD;
    }

    @Override
    public BigDecimal getPrice() {
        return BigDecimal.valueOf(15L);
    }
}
