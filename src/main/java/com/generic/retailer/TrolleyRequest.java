package com.generic.retailer;

import java.util.List;

public class TrolleyRequest {

    private List<TrolleyItemRequest> items;

    public TrolleyRequest(List<TrolleyItemRequest> items) {
        this.items = items;
    }

    public TrolleyRequest() {
    }

    public List<TrolleyItemRequest> getItems() {
        return items;
    }

    public void setItems(List<TrolleyItemRequest> items) {
        this.items = items;
    }
}
