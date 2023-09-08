package br.com.wellcar.controller;

import br.com.wellcar.entity.Budget;
import br.com.wellcar.service.BudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/budget")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BudgetController {

    @Autowired
    private BudgetService service;

    @PostMapping("/{clientId}/{carId}")
    public ResponseEntity<Budget> create(@PathVariable Long clientId, @PathVariable Long carId, @RequestBody Budget budget) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createBudget(clientId, carId, budget));
    }

    @GetMapping("/all-budgets")
    public ResponseEntity<List<Budget>> findAll() {
        return ResponseEntity.ok().body(service.findAllBudget());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Budget> update(@PathVariable Long id, @RequestBody Budget updateBudget) {
        return ResponseEntity.ok().body(service.updateBudget(id, updateBudget));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.deleteBudget(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Orçamento excluido com sucesso!");
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/{budgetId}/product/{prodId}")
    public ResponseEntity<Budget> addProduct(@PathVariable Long budgetId, @PathVariable Long prodId) {
        return ResponseEntity.ok().body(service.addProdToBudget(budgetId, prodId));
    }

    @PostMapping("/{budgetId}/labor/{laborId}")
    public ResponseEntity<Budget> addLabor(@PathVariable Long budgetId, @PathVariable Long laborId) {
        return ResponseEntity.ok().body(service.addLaborToBudget(budgetId, laborId));
    }
}
