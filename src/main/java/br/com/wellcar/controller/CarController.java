package br.com.wellcar.controller;

import br.com.wellcar.entity.Car;

import br.com.wellcar.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/car")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Wellcar Cars", description = "Endpoints for controlling incoming cars")
public class CarController {

    @Autowired
    private CarService service;

    @PostMapping("/update/{id}")
    @Operation(summary = "Update car data through ID")
    public ResponseEntity<Car> update(@PathVariable Long id, @RequestBody Car UpdateCar) {
        return ResponseEntity.ok().body(service.updateCar(id, UpdateCar));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a car using id")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.deleteCar(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Carro excluido com sucesso!");
        return ResponseEntity.ok().body(body);
    }


}
