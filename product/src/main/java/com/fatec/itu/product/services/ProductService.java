package com.fatec.itu.product.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.itu.product.entities.Product;
import com.fatec.itu.product.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
    private final ProductRepository repository;

    //Injetando o repositorio
    ProductService(ProductRepository repository) {
        this.repository = repository;
    }
    //Método do service; expondo produtos por id
     public Product findById(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new EntityNotFoundException());
    }

    //Método que mostra todos o produtos
    public List<Product> findAll(){
        return repository.findAll();
    }
    public void deleteById(Long id)
    {
        //confirma se o id existe e apaga ele
        if(repository.existsById(id))
            repository.deleteById(id);
        else
           throw new EntityNotFoundException("Produto não cadastrado");
    }

    public Product save(Product product)
    {
         return repository.save(product);
    }

    public void update(Product product, Long id)
    {
        Product p  = repository.findById(id)
                    //verifica se o id existe. Se existir executa o resto, caso não, ele para aqui 
                               .orElseThrow(() -> new EntityNotFoundException("Produto não cadastrado")); 

        p.setDescription(product.getDescription());                                
        p.setName(product.getName());
        p.setPrice(product.getPrice());

        repository.save(p);


    }
}
