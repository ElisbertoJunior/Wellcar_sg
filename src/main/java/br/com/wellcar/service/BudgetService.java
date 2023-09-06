package br.com.wellcar.service;

import br.com.wellcar.entity.Budget;
import br.com.wellcar.entity.Car;
import br.com.wellcar.entity.Client;
import br.com.wellcar.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository repository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CarService carService;

    public Budget cretaBudget(Long clientId, Long carId, Budget budget) {
        Client client = clientService.findClientById(clientId);
        Car car = carService.findCarById(carId);

        budget.setClient(client);
        budget.setCar(car);

        return repository.save(budget);
    }


}
