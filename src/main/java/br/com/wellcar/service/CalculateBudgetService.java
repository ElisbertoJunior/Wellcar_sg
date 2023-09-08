package br.com.wellcar.service;

import br.com.wellcar.entity.Budget;
import br.com.wellcar.entity.Labor;
import br.com.wellcar.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateBudgetService {

    public static Double getBill(Budget budget) {
        return getBill(budget.getProducts(), budget.getLabors());
    }

    private static Double getBill(List<Product> products, List<Labor> labors) {
        Double totalProducts = 0.00;
        Double totalLabors = 0.00;
        Double bill;
        for (Product product : products) {
            totalProducts += product.getPrice();
        }

        for (Labor labor : labors) {
            totalLabors += labor.getPrice();
        }

        bill = totalProducts + totalLabors;

        return bill;


    }


}
