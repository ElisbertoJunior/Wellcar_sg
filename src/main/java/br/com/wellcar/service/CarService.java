package br.com.wellcar.service;

import br.com.wellcar.entity.Car;
import br.com.wellcar.exception.CarNullException;
import br.com.wellcar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    @Autowired
    private CarRepository repository;

    public void registerCar(Car car) {
        repository.save(car);
    }

    public Car findCarById(Long id) {
        return repository.findById(id).orElseThrow(CarNullException::new);
    }

    public Car updateCar(Long id, Car entryCar) {
        Car updateCar = findCarById(id);

        updateCar.setBrand(entryCar.getBrand());
        updateCar.setModel(entryCar.getModel());
        updateCar.setLicense(entryCar.getLicense());
        updateCar.setColor(entryCar.getColor());
        updateCar.setAgeModel(entryCar.getAgeModel());

        return repository.save(updateCar);
    }

    public void deleteCar(long id) {
        Car car = findCarById(id);
        repository.delete(car);
    }



}
