package br.com.wellcar.controller;

import br.com.wellcar.entity.Car;
import br.com.wellcar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CarController {

    @Autowired
    private CarService service;

    @PostMapping("/save/{client_id}")
    public ResponseEntity<Car> register(@PathVariable Long client_id, @RequestBody Car car) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerCar(client_id, car));
    }
}
