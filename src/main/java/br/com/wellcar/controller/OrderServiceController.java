package br.com.wellcar.controller;

import br.com.wellcar.entity.OrderService;
import br.com.wellcar.service.OrderServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/os")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderServiceController {

    @Autowired
    private OrderServiceService service;

    @PostMapping("/create/{budgetId}")
    public ResponseEntity<OrderService> create(@PathVariable Long budgetId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createOS(budgetId));
    }

    @GetMapping("/all-orders")
    public ResponseEntity<List<OrderService>> findAll() {
        return ResponseEntity.ok().body(service.findAllOS());
    }

    @PostMapping("/finish/{id}")
    public ResponseEntity<OrderService> finishOS(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.finalizeOS(id));
    }
}
