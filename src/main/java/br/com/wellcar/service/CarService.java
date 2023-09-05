package br.com.wellcar.service;

import br.com.wellcar.entity.Car;
import br.com.wellcar.entity.Client;
import br.com.wellcar.repository.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private CarRepository repository;

    private ClientService clientService;

    public Car registerCar(Long client_id, Car car) {
        Client client = clientService.findClientById(client_id);
        car.setClient(client);
        return repository.save(car);
    }

}
