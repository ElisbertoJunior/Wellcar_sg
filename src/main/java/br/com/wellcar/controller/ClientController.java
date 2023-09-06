package br.com.wellcar.controller;

import br.com.wellcar.entity.Car;
import br.com.wellcar.entity.Client;
import br.com.wellcar.repository.CarRepository;
import br.com.wellcar.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findClientById(id));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client entryCLient) {
        return ResponseEntity.ok().body(service.updateClient(id, entryCLient));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<List<Client>> delete(@PathVariable Long id) {
        service.deleteClient(id);
        return ResponseEntity.ok().body(service.findAllClients());
    }

    @PostMapping("/{clientId}/cars")
    public ResponseEntity<Client> addCarToClient(@PathVariable Long clientId, @RequestBody Car carToAdd) {
        Client updatedClient = service.addCarToClient(clientId, carToAdd);
        return ResponseEntity.ok().body(updatedClient);
    }

}
