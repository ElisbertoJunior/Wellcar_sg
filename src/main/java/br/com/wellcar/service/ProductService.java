package br.com.wellcar.service;

import br.com.wellcar.entity.Product;
import br.com.wellcar.exception.ProductNullException;
import br.com.wellcar.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product registerProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    public Product findProduct(Long id)  {
        return repository.findById(id).orElseThrow(ProductNullException::new);
    }

    public Product updateProduct(Long id, Product updateProd)  {
        Product currentProd = findProduct(id);

        currentProd.setDescription(updateProd.getDescription());
        currentProd.setPrice(updateProd.getPrice());

        return repository.save(currentProd);
    }

    public void deleteProduct(Long id) {
        Product product = findProduct(id);
        repository.delete(product);
    }

}
