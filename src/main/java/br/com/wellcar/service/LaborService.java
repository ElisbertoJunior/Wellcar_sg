package br.com.wellcar.service;

import br.com.wellcar.entity.Labor;
import br.com.wellcar.repository.LaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaborService {

    @Autowired
    private LaborRepository repository;

    public Labor registerService(Labor labor) {
        return repository.save(labor);
    }
}
