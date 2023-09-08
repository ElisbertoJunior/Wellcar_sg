package br.com.wellcar.service;

import br.com.wellcar.entity.Labor;
import br.com.wellcar.exception.ProductNullException;
import br.com.wellcar.repository.LaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaborService {

    @Autowired
    private LaborRepository repository;

    public Labor registerService(Labor labor) {
        return repository.save(labor);
    }

    public List<Labor> findAllLabors() {
        return repository.findAll();
    }

    public Labor findLabor(Long id)  {
        return repository.findById(id).orElseThrow(ProductNullException::new);
    }

    public Labor updateLabor(Long id, Labor updateLabor)  {
        Labor currentLabor = findLabor(id);

        currentLabor.setDescription(updateLabor.getDescription());
        currentLabor.setPrice(updateLabor.getPrice());

        return repository.save(currentLabor);
    }

    public void deleteLabor(Long id) {
        Labor labor = findLabor(id);
        repository.delete(labor);
    }
}
