package br.com.wellcar.service;

import br.com.wellcar.entity.Client;
import br.com.wellcar.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client registerClient(Client client) {
        return repository.save(client);
    }
}
