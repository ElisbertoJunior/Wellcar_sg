package br.com.wellcar.controller;

import br.com.wellcar.entity.Report;
import br.com.wellcar.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Report> createByPeriod(@RequestBody Report report) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createReportByPeriod(report));
    }

    @PostMapping("/is-open")
    public ResponseEntity<Report> createReportIsOpenOrders() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createReportOpenOrders());
    }

    @GetMapping("/all-reports")
    public ResponseEntity<List<Report>> findAllReports() {
        return ResponseEntity.ok().body(service.findAllReports());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteReport(@PathVariable Long id) {
        service.deleteReport(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Relatorio excluido com sucesso.");
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> find(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findReport(id));
    }
}
