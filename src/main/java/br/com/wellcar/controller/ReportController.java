package br.com.wellcar.controller;

import br.com.wellcar.entity.Report;
import br.com.wellcar.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/report")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportController {

    @Autowired
    private ReportService service;

    @PostMapping("/create")
    public ResponseEntity<Report> create() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createReport());
    }

    @PostMapping("/period")
    public ResponseEntity<Report> createByPeriod(@RequestBody LocalDate initialDate, @RequestBody LocalDate finalDate) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createReportByPeriod(initialDate, finalDate));
    }

    @PostMapping("/is-open")
    public ResponseEntity<Report> createReportIsOpenOrders() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createReportOpenOrders());
    }

    @GetMapping("/all-reports")
    public ResponseEntity<List<Report>> findAllReports() {
        return ResponseEntity.ok().body(service.findAllReports());
    }
}
