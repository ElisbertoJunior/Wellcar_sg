package br.com.wellcar.controller;

import br.com.wellcar.entity.ServiceOrder;
import br.com.wellcar.service.ServiceOrderService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/os")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ServiceOrderController {

    @Autowired
    private ServiceOrderService service;

    @PostMapping("/create/{budgetId}")
    @Operation(summary = "Create new order service")
    public ResponseEntity<ServiceOrder> create(@PathVariable Long budgetId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createOS(budgetId));
    }

    @GetMapping("/all-orders")
    @Operation(summary = "Returns all service orders from the database")
    public ResponseEntity<List<ServiceOrder>> findAll() {
        return ResponseEntity.ok().body(service.findAllOS());
    }

    @PostMapping("/finish/{id}")
    @Operation(summary = "Completes a service order")
    public ResponseEntity<ServiceOrder> finishOS(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.finalizeOS(id));
    }
}
