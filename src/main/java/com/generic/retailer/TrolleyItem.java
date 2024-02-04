package com.generic.retailer;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class TrolleyItem {

    private Product product;

    private Integer quantity;

    private static final BigDecimal THURSDAY_DISCOUNT_PERCENTAGE = BigDecimal.valueOf(20);

    public TrolleyItem(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public BigDecimal getTotal() {
        return this.product.getPrice().multiply(BigDecimal.valueOf(this.quantity));
    }

    public BigDecimal getDiscount() {
        BigDecimal discount = BigDecimal.ZERO;
        if (LocalDate.now().getDayOfWeek() == DayOfWeek.THURSDAY) {
            BigDecimal total = getTotal();
            return total.multiply(THURSDAY_DISCOUNT_PERCENTAGE).divide(BigDecimal.valueOf(100l));
        }
        return discount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
