package br.com.wellcar.controller;

import br.com.wellcar.entity.Report;
import br.com.wellcar.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Wellcar Reports", description = "Endpoints for controlling application reports")
public class ReportController {

    @Autowired
    private ReportService service;

    @PostMapping("/create")
    @Operation(summary = "Creates a new global report")
    public ResponseEntity<Report> create() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createReport());
    }

    @PostMapping("/period")
    @Operation(summary = "Creates a report for specified period")
    public ResponseEntity<Report> createByPeriod(@RequestBody Report report) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createReportByPeriod(report));
    }

    @PostMapping("/is-open")
    @Operation(summary = "create a report of all open service orders")
    public ResponseEntity<Report> createReportIsOpenOrders() {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createReportOpenOrders());
    }

    @GetMapping("/all-reports")
    @Operation(summary = "Returns a list of all generated reports")
    public ResponseEntity<List<Report>> findAllReports() {
        return ResponseEntity.ok().body(service.findAllReports());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a specific report")
    public ResponseEntity<Object> deleteReport(@PathVariable Long id) {
        service.deleteReport(id);
        Map<String, Object> body = new HashMap<>();
        body.put("message", "Relatorio excluido com sucesso.");
        return ResponseEntity.ok().body(body);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a specific report")
    public ResponseEntity<Report> find(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findReport(id));
    }
}
