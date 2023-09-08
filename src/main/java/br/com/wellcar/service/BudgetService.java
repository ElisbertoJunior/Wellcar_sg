package br.com.wellcar.service;

import br.com.wellcar.entity.*;
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

    @Autowired
    private ProductService productService;

    @Autowired
    private LaborService laborService;

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

    public Budget addProdToBudget(Long budgetId, Long productId) {
        Budget budget = findBudgetById(budgetId);
        Product product = productService.findProduct(productId);

        List<Product> products = budget.getProducts();
        products.add(product);

        budget.setProducts(products);
        return repository.save(budget);
    }

    public Budget addLaborToBudget(Long budgetId, Long laborId) {
        Budget budget = findBudgetById(budgetId);
        Labor labor = laborService.findLabor(laborId);

        List<Labor> labors = budget.getLabors();
        labors.add(labor);

        budget.setLabors(labors);
        return repository.save(budget);
    }


}
