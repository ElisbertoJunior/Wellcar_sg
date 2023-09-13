package br.com.wellcar.service;

import br.com.wellcar.entity.Budget;
import br.com.wellcar.entity.OrderService;
import br.com.wellcar.repository.OrderServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceService {

    @Autowired
    private OrderServiceRepository repository;

    @Autowired
    private BudgetService budgetService;

    public OrderService createOS(Long budgetId) {
        Budget selectBudget = budgetService.findBudgetById(budgetId);
        selectBudget.setIsApproved(true);
        OrderService newOS = new OrderService();

        newOS.setBudget(selectBudget);
        newOS.setCreatedAt(LocalDateTime.now());
        newOS.setStatus(setOrderStatus(newOS));

        return repository.save(newOS);

    }

    public List<OrderService> findAllOS() {
        return repository.findAll();
    }

    public OrderService findOS(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NullPointerException("A ordem de serviço com o ID: " + id + " não foi encontrada.")
        );
    }

    public OrderService finalizeOS(Long id) {
        OrderService os = findOS(id);
        os.setFinishedAt(LocalDateTime.now());
        os.setStatus(setOrderStatus(os));

        return repository.save(os);
    }

    public String setOrderStatus(OrderService os) {
        if (os.getFinishedAt() != null) {
            return "Finalizado";
        }

        return "Em aberto";
    }

}
