package com.generic.retailer;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public final class Trolley {

    private static Trolley INSTANCE = null;

    private Map<ProductType, TrolleyItem> items;

    public static Trolley getInstance() {
        if (INSTANCE == null) {
            return new Trolley(new HashMap<>(3));
        }
        return INSTANCE;
    }

    private Trolley(Map<ProductType, TrolleyItem> items) {
        this.items = items;
    }

    public Map<ProductType, TrolleyItem> getItems() {
        return items;
    }


    public void add(Product product) {
        if (items.containsKey(product.getType())) {
            items.get(product.getType()).increaseQuantity();
        } else {
            items.put(product.getType(), new TrolleyItem(product, 1));
        }
    }

    public BigDecimal applyDiscount(Discount discount) {
        BigDecimal discountAmount = BigDecimal.ZERO;
        for (TrolleyItem item : this.items.values()) {
            discountAmount = discountAmount.add(discount.getDiscountAmount(item));
        }
        return discountAmount;
    }

    public BigDecimal calculateTotal() {
        if (items.size() == 0) {
            return BigDecimal.ZERO;
        } else {
            BigDecimal total = BigDecimal.ZERO;
            for (TrolleyItem trolleyItem : items.values()) {
                total = total.add(trolleyItem.getTotal());
            }
            return total;
        }
    }
}