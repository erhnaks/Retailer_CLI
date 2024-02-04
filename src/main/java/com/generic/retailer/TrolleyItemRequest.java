package com.generic.retailer;

public class TrolleyItemRequest {

    private ProductType type;
    private Integer quantity;

    public TrolleyItemRequest(ProductType type, Integer quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public TrolleyItemRequest() {
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
