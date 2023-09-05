package br.com.wellcar.service;

import br.com.wellcar.entity.Car;
import br.com.wellcar.entity.Client;
import br.com.wellcar.repository.CarRepository;
import br.com.wellcar.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;


    public Car registerCar(Car car) {
       return repository.save(car);
    }



}
