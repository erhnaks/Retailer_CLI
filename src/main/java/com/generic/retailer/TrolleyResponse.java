package com.generic.retailer;

import java.math.BigDecimal;
import java.util.List;

public class TrolleyResponse {

    private List<TrolleyItem> items;
    private BigDecimal totalDiscount;

    public TrolleyResponse(List<TrolleyItem> items, BigDecimal totalDiscount) {
        this.items = items;
        this.totalDiscount = totalDiscount;
    }

    public TrolleyResponse() {
    }

    public List<TrolleyItem> getItems() {
        return items;
    }

    public void setItems(List<TrolleyItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }
}
