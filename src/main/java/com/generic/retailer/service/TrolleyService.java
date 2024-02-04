package com.generic.retailer.service;

import com.generic.retailer.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TrolleyService {

    public List<TrolleyResponse> getTrolleys() {
        Database database = Database.getInstance();
        List<Trolley> trolleys = database.findAll();
        List<TrolleyResponse> trolleyResponses = trolleys.stream()
                .map(trolley -> {
                    BigDecimal thursdayDiscount = trolley.applyDiscount(new ThursdayDiscount(LocalDate.now()));
                    BigDecimal twoForOneDiscount = trolley.applyDiscount(new TwoForOneDiscount());
                    return new TrolleyResponse(new ArrayList<>(trolley.getItems().values()), thursdayDiscount.add(twoForOneDiscount));
                })
                .collect(Collectors.toList());
        return trolleyResponses;
    }

    public void createTrolley(TrolleyRequest trolleyRequest) {
        Database database = Database.getInstance();
        Map<ProductType, TrolleyItem> trolleyItems = new HashMap<>();
        trolleyRequest.getItems().stream().forEach(trolleyItem -> trolleyItems.put(trolleyItem.getType(),
                new TrolleyItem(ProductUtil.buildProduct(trolleyItem.getType()), trolleyItem.getQuantity())));
        Trolley trolley = new Trolley();
        trolley.setItems(trolleyItems);
        database.add(trolley);
    }
}
