package br.com.wellcar.service;

import br.com.wellcar.entity.OrderService;
import br.com.wellcar.entity.Report;
import br.com.wellcar.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repository;

    @Autowired
    private OrderServiceService orderService;

    public Report createReport() {
        List<OrderService> orders = orderService.findAllOS();
        Report newReport = new Report();

        List<OrderService> list = new ArrayList<>();
        for (OrderService item : orders) {
            list.add(item);
        }

        Double totalReport = 0.00;
        for (OrderService item : list) {
            totalReport += item.getBudget().getTotalValue();
        }

        newReport.setOrders(list);
        newReport.setTotalBilled(totalReport);

        return repository.save(newReport);
    }

    public Report createReportOpenOrders() {
        List<OrderService> orders = orderService.findAllOS();
        Report newReport = new Report();

        List<OrderService> ordersOpened = new ArrayList<>();
        for(OrderService order : orders) {
            if (order.getStatus().equals("Em aberto")) {
                ordersOpened.add(order);
            }
        }

        Double totalReport = 0.00;
        for (OrderService item : ordersOpened) {
            totalReport += item.getBudget().getTotalValue();
        }

        newReport.setOrders(ordersOpened);
        newReport.setTotalBilled(totalReport);

        return repository.save(newReport);

    }
}
