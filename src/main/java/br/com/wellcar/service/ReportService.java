package br.com.wellcar.service;

import br.com.wellcar.entity.ServiceOrder;
import br.com.wellcar.entity.Report;
import br.com.wellcar.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository repository;

    @Autowired
    private ServiceOrderService orderService;

    public Report createReport() {
        List<ServiceOrder> orders = orderService.findAllOS();
        Report newReport = new Report();

        List<ServiceOrder> list = new ArrayList<>();
        for (ServiceOrder item : orders) {
            list.add(item);
        }


        newReport.setOrders(list);
        newReport.setTotalBilled(CalculateService.getReportBill(list));

        return repository.save(newReport);
    }

    public Report createReportOpenOrders() {
        List<ServiceOrder> orders = orderService.findAllOS();
        Report newReport = new Report();

        List<ServiceOrder> ordersOpened = new ArrayList<>();
        for(ServiceOrder order : orders) {
            if (order.getStatus().equals("Em aberto")) {
                ordersOpened.add(order);
            }
        }


        newReport.setOrders(ordersOpened);
        newReport.setTotalBilled(CalculateService.getReportBill(ordersOpened));

        return repository.save(newReport);

    }

    public List<Report> findAllReports() {
        return repository.findAll();
    }

    public Report createReportByPeriod(Report newReport) {
        List<ServiceOrder> orders = orderService.findAllOS();
        boolean noOrdersInPeriod = true;

        List<ServiceOrder> ordersInPeriod = new ArrayList<>();
        for(ServiceOrder order : orders) {
            LocalDate orderDate = LocalDate.from(order.getFinishedAt());

            if(orderDate.isAfter(newReport.getInitialDate()) && orderDate.isBefore(newReport.getFinalDate())) {
                ordersInPeriod.add(order);
                noOrdersInPeriod = false;
            }

        }

        if(noOrdersInPeriod) {
            throw new NullPointerException("Nenhuma ordem encontrada no período especificado.");
        }

        newReport.setOrders(ordersInPeriod);
        newReport.setTotalBilled(CalculateService.getReportBill(ordersInPeriod));

        return repository.save(newReport);
    }

    public Report findReport(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NullPointerException("O relatorio com o ID: " + id + " não foi encontrado.")
        );
    }

    public void deleteReport(Long id) {
        repository.deleteById(id);
    }
}
