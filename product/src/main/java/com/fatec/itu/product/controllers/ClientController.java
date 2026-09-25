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

import com.fatec.itu.product.entities.Client;
import com.fatec.itu.product.entities.Product;
import com.fatec.itu.product.services.ClientService;


@RestController
@RequestMapping("/clients")
public class ClientController {

    //Injetando service
    private final ClientService service;

    ClientController(ClientService service) {
        this.service = service;
    }

    //Reponde requisição http get de todos o produtos
    @GetMapping
    public ResponseEntity<List<Client>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    //Expondo id; respondendo requisição http get por id
    @GetMapping("{id}")
    public ResponseEntity<Client> getById(@PathVariable long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("{id}")
    //void não devolve nada
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build(); // noContent - status code HTTP 204
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody Client client)
    {
        Client c = service.save(client);
       
        URI location = ServletUriComponentsBuilder //localização do novo produto que está criando
                       .fromCurrentRequest()
                       .path("/{id}")
                       .buildAndExpand(c.getId())
                       .toUri();
        
        return ResponseEntity.created(location).body(c); // created - status code HTTP 201


    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@PathVariable long id,
                                       @RequestBody Client client)
    {
            service.update(client, id);
            return ResponseEntity.noContent().build();
    }
    
}
