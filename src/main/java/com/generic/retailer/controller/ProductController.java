package com.generic.retailer.controller;

import com.generic.retailer.Book;
import com.generic.retailer.CD;
import com.generic.retailer.DVD;
import com.generic.retailer.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api")
public class ProductController {

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(Arrays.asList(new Book(), new CD(), new DVD()));
    }
}
