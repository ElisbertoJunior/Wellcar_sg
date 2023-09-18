package br.com.wellcar.controller;

import br.com.wellcar.entity.Labor;
import br.com.wellcar.service.LaborService;
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
@RequestMapping("/labor")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Wellcar Labors", description = "Endpoints to control labors")
public class LaborController {

    @Autowired
    private LaborService service;

    @PostMapping("/create")
    @Operation(summary = "Endpoint to create a labor")
    public ResponseEntity<Labor> create(@RequestBody Labor labor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerService(labor));
    }

    @GetMapping("/all-labors")
    @Operation(summary = "Returns all database labors")
    public ResponseEntity<List<Labor>> findAll() {
        return ResponseEntity.ok().body(service.findAllLabors());
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Updates data from a specific database labor")
    public ResponseEntity<Labor> update(@PathVariable Long id, @RequestBody Labor updateLabor) {
        return ResponseEntity.ok().body(service.updateLabor(id, updateLabor));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Brings a specific labor from the database")
    public ResponseEntity<Labor> find(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findLabor(id));
    }

    @DeleteMapping("delete/{id}")
    @Operation(summary = "Deletes a specific labor from the database")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.deleteLabor(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Mao de obra excluida com sucesso!");
        return ResponseEntity.ok().body(body);
    }


}
