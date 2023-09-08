package br.com.wellcar.controller;

import br.com.wellcar.entity.Product;
import br.com.wellcar.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerProduct(product));
    }

    @GetMapping("/all-products")
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok().body(service.findAllProducts());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product updateProd) {
        return ResponseEntity.ok().body(service.updateProduct(id, updateProd));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> find(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findProduct(id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.deleteProduct(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Produto excluido com sucesso!");
        return ResponseEntity.ok().body(body);
    }

}
