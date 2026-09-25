package com.fatec.itu.product.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fatec.itu.product.entities.Client;
import com.fatec.itu.product.entities.Product;
import com.fatec.itu.product.repositories.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientService {

     private final ClientRepository repository;

    //Injetando o repositorio
    ClientService(ClientRepository repository) {
        this.repository = repository;
    }
    //Método do service; expondo produtos por id
     public Client findById(Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new EntityNotFoundException());
    }

    //Método que mostra todos o produtos
    public List<Client> findAll(){
        return repository.findAll();
    }

    public void deleteById(Long id)
    {
        //confirma se o id existe e apaga ele
        if(repository.existsById(id))
            repository.deleteById(id);
        else
           throw new EntityNotFoundException("Cliente não cadastrado");
    }

    public Client save(Client client)
    {
         return repository.save(client);
    }

    public void update(Client client, Long id)
    {
        Client c  = repository.findById(id)
                    //verifica se o id existe. Se existir executa o resto, caso não, ele para aqui 
                               .orElseThrow(() -> new EntityNotFoundException("Ciente não cadastrado")); 

        c.setTelephone(client.getTelephone());                                
        c.setName(client.getName());
        c.setAge(client.getAge());
        c.setAdress(client.getAdress());
        c.setGender(client.getGender());

        repository.save(c);


    }
    
}
