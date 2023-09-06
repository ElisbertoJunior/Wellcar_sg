package br.com.wellcar.controller;

import br.com.wellcar.entity.Budget;
import br.com.wellcar.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/budget")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BudgetController {

    @Autowired
    private BudgetService service;

    @PostMapping("/{clientId}/{carId}")
    public ResponseEntity<Budget> createBudget(@PathVariable Long clientId, @PathVariable Long carId, @RequestBody Budget budget) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cretaBudget(clientId, carId, budget));
    }
}
