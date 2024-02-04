package com.generic.retailer;

public class ProductUtil {

    public static Product buildProduct(ProductType productType) {
        return switch (productType) {
            case BOOK -> new Book();
            case CD -> new CD();
            case DVD -> new DVD();
            default -> null;
        };
    }
}
