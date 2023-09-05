package br.com.wellcar.service;

import br.com.wellcar.entity.Client;
import br.com.wellcar.exception.ClientNullException;
import br.com.wellcar.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client registerClient(Client client) {
        if(client.getName() == null || client.getPhone() == null || client.getCpf() == null || client.getEmail() == null)
            throw new ClientNullException();
        return repository.save(client);
    }

    public List<Client> findAllClients() {
        return repository.findAll();
    }

    public Client findClientById(Long id) {
        return repository.findById(id).orElseThrow();

    }
}
