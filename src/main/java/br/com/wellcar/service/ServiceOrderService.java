package br.com.wellcar.service;

import br.com.wellcar.entity.Budget;
import br.com.wellcar.entity.ServiceOrder;
import br.com.wellcar.repository.ServiceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository repository;

    @Autowired
    private BudgetService budgetService;

    public ServiceOrder createOS(Long budgetId) {
        Budget selectBudget = budgetService.findBudgetById(budgetId);
        selectBudget.setIsApproved(true);
        ServiceOrder newOS = new ServiceOrder();

        newOS.setBudget(selectBudget);
        newOS.setCreatedAt(LocalDateTime.now());
        newOS.setStatus(setOrderStatus(newOS));

        return repository.save(newOS);

    }

    public List<ServiceOrder> findAllOS() {
        return repository.findAll();
    }

    public ServiceOrder findOS(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NullPointerException("A ordem de serviço com o ID: " + id + " não foi encontrada.")
        );
    }

    public ServiceOrder finalizeOS(Long id) {
        ServiceOrder os = findOS(id);
        os.setFinishedAt(LocalDateTime.now());
        os.setStatus(setOrderStatus(os));

        return repository.save(os);
    }

    public String setOrderStatus(ServiceOrder os) {
        if (os.getFinishedAt() != null) {
            return "Finalizado";
        }

        return "Em aberto";
    }

}
