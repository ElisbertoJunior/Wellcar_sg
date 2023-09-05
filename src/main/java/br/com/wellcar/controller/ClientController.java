package br.com.wellcar.controller;

import br.com.wellcar.entity.Client;
import br.com.wellcar.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientController {


    @Autowired
    private ClientService service;

    @PostMapping("/save")
    public ResponseEntity<Client> register(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerClient(client));
    }

    @GetMapping("/all-clients")
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok().body(service.findAllClients());
    }

}
