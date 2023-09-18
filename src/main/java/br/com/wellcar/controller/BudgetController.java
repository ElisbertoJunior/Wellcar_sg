package br.com.wellcar.controller;

import br.com.wellcar.entity.Budget;
import br.com.wellcar.service.BudgetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Wellcar Budgets", description = "Endpoints to control budgets")
public class BudgetController {

    @Autowired
    private BudgetService service;

    @PostMapping("/{clientId}/{carId}")
    @Operation(summary = "Create a budget for a specific car and customer")
    public ResponseEntity<Budget> create(@PathVariable Long clientId, @PathVariable Long carId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createBudget(clientId, carId));
    }

    @GetMapping("/all-budgets")
    @Operation(summary = "Returns a list of all budgets in the database")
    public ResponseEntity<List<Budget>> findAll() {
        return ResponseEntity.ok().body(service.findAllBudget());
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update a specific budget in the database")
    public ResponseEntity<Budget> update(@PathVariable Long id, @RequestBody Budget updateBudget) {
        return ResponseEntity.ok().body(service.updateBudget(id, updateBudget));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a specific budget from the database")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.deleteBudget(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Orçamento excluido com sucesso!");
        return ResponseEntity.ok().body(body);
    }

    @PostMapping("/{budgetId}/product/{prodId}")
    @Operation(summary = "Add a product to a specific budget in the database")
    public ResponseEntity<Budget> addProduct(@PathVariable Long budgetId, @PathVariable Long prodId) {
        return ResponseEntity.ok().body(service.addProdToBudget(budgetId, prodId));
    }

    @PostMapping("/{budgetId}/labor/{laborId}")
    @Operation(summary = "Add a labor to a specific budget in the database")
    public ResponseEntity<Budget> addLabor(@PathVariable Long budgetId, @PathVariable Long laborId) {
        return ResponseEntity.ok().body(service.addLaborToBudget(budgetId, laborId));
    }
}
