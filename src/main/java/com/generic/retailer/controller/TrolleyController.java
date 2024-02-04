package com.generic.retailer.controller;

import com.generic.retailer.TrolleyRequest;
import com.generic.retailer.TrolleyResponse;
import com.generic.retailer.service.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class TrolleyController {

    @Autowired
    private TrolleyService trolleyService;

    @GetMapping("/trolleys")
    public ResponseEntity<List<TrolleyResponse>> getTrolleys() {
        return ResponseEntity.ok(trolleyService.getTrolleys());
    }

    @PostMapping("/trolley")
    public ResponseEntity getTrolley(@RequestBody TrolleyRequest trolleyRequest) {
        trolleyService.createTrolley(trolleyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
