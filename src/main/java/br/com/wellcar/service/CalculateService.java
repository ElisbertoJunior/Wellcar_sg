package br.com.wellcar.service;

import br.com.wellcar.entity.Budget;
import br.com.wellcar.entity.Labor;
import br.com.wellcar.entity.ServiceOrder;
import br.com.wellcar.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateService {

    public static Double getBill(Budget budget) {
        return getBill(budget.getProducts(), budget.getLabors());
    }

    public static Double getReportBill(List<ServiceOrder> orders) {
        return getCalculateReportBill(orders);
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

    private static Double getCalculateReportBill(List<ServiceOrder> orders) {
        Double totalReport = 0.00;
        for (ServiceOrder item : orders) {
            totalReport += item.getBudget().getTotalValue();
        }

        return totalReport;
    }


}
