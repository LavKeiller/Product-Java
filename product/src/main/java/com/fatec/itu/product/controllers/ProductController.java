package com.fatec.itu.product.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fatec.itu.product.entities.Product;
import com.fatec.itu.product.services.ProductService;

//o @ define o a função de página
@RestController
@RequestMapping("/products")  //define qual parte ele mapeia (produtos nesse caso)
public class ProductController {

    //Injetando service
    private final ProductService service;

    ProductController(ProductService service) {
        this.service = service;
    }

    //Reponde requisição http get de todos o produtos
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    //Expondo id; respondendo requisição http get por id
    @GetMapping("{id}")
    public ResponseEntity<Product> getById(@PathVariable long id){
        return ResponseEntity.ok(service.findById(id)); // ok - status code HTTP 200
    }
    
//request(pedido) tem head e body | response(resposta) tem head e body

//passa o id
    @DeleteMapping("{id}")
    //void não devolve nada
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build(); // noContent - status code HTTP 204
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product)
    {
        Product p = service.save(product);
       
        URI location = ServletUriComponentsBuilder //localização do novo produto que está criando
                       .fromCurrentRequest()
                       .path("/{id}")
                       .buildAndExpand(p.getId())
                       .toUri();
        
        return ResponseEntity.created(location).body(p); // created - status code HTTP 201


    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
                                       @RequestBody Product product)
    {
            service.update(product, id);
            return ResponseEntity.noContent().build();
    }
}
