package br.com.wellcar.controller;

import br.com.wellcar.entity.Report;
import br.com.wellcar.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
