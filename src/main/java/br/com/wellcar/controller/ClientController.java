package br.com.wellcar.controller;

import br.com.wellcar.entity.Car;
import br.com.wellcar.entity.Client;
import br.com.wellcar.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Wellcar SG", description = "Endpoints for client control")
public class ClientController {

    @Autowired
    private ClientService service;

    @PostMapping("/save")
    @Operation(summary = "Add new client to the database")
    public ResponseEntity<Client> register(@RequestBody Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registerClient(client));
    }

    @GetMapping("/all-clients")
    @Operation(summary = "List all clients in the database")
    public ResponseEntity<List<Client>> findAll() {
        return ResponseEntity.ok().body(service.findAllClients());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Returns a client searched by id")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findClientById(id));
    }

    @PostMapping("/update/{id}")
    @Operation(summary = "Update client data using id")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody Client entryCLient) {
        return ResponseEntity.ok().body(service.updateClient(id, entryCLient));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deletes a client using id")
    public ResponseEntity<List<Client>> delete(@PathVariable Long id) {
        service.deleteClient(id);
        return ResponseEntity.ok().body(service.findAllClients());
    }

    @PostMapping("/{clientId}/cars")
    @Operation(summary = "Add a car to a specific client using id")
    public ResponseEntity<Client> addCarToClient(@PathVariable Long clientId, @RequestBody Car carToAdd) {
        Client updatedClient = service.addCarToClient(clientId, carToAdd);
        return ResponseEntity.ok().body(updatedClient);
    }

}
