package br.com.wellcar.service;

import br.com.wellcar.entity.Car;
import br.com.wellcar.entity.Client;
import br.com.wellcar.exception.ClientNullException;
import br.com.wellcar.exception.FindClientNullException;
import br.com.wellcar.repository.CarRepository;
import br.com.wellcar.repository.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Autowired
    private CarService carService;

    public Client registerClient(Client client) {
        if(client.getName() == null || client.getPhone() == null || client.getCpf() == null || client.getEmail() == null)
            throw new ClientNullException();
        return repository.save(client);
    }

    public List<Client> findAllClients() {
        return repository.findAll();
    }

    public Client findClientById(Long id) {
        return repository.findById(id).orElseThrow(FindClientNullException::new);
    }

    public Client updateClient(Long id, Client entryClient) {
        Client client = findClientById(id);

        client.setName(entryClient.getName());
        client.setPhone(entryClient.getPhone());
        client.setCpf(entryClient.getCpf());
        client.setEmail(entryClient.getEmail());

        return repository.save(client);

    }

    public void deleteClient(Long id) {
        Client client = findClientById(id);
        repository.delete(client);
    }

    public Client addCarToClient(Long clientId, Car carToAdd) {
        Client client = findClientById(clientId);

        List<Car> cars = client.getCars();
        cars.add(carToAdd);

        carService.registerCar(carToAdd);
        client.setCars(cars);
        return repository.save(client);
    }
}
