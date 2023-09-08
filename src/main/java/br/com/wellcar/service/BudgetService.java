package br.com.wellcar.service;

import br.com.wellcar.entity.Budget;
import br.com.wellcar.entity.Car;
import br.com.wellcar.entity.Client;
import br.com.wellcar.exception.BudgetNullException;
import br.com.wellcar.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository repository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CarService carService;

    public Budget createBudget(Long clientId, Long carId, Budget budget) {
        Client client = clientService.findClientById(clientId);
        Car car = carService.findCarById(carId);

        budget.setClient(client);
        budget.setCar(car);
        budget.setCreationDate(LocalDateTime.now());

        return repository.save(budget);
    }

    public List<Budget> findAllBudget() {
        return repository.findAll();
    }

    public Budget findBudgetById(Long id) {
        return repository.findById(id).orElseThrow(BudgetNullException::new);
    }
    public Budget updateBudget(Long id, Budget updateBudget) {
        Budget currentBudget = findBudgetById(id);

        currentBudget.setTotalValue(updateBudget.getTotalValue());
        return repository.save(currentBudget);
    }

    public void deleteBudget(Long id) {
        Budget budget = findBudgetById(id);
        repository.delete(budget);
    }


}
