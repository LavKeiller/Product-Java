package com.fatec.itu.product.mappers;

import com.fatec.itu.product.dtos.ClientRequest;
import com.fatec.itu.product.dtos.ClientResponse;
import com.fatec.itu.product.entities.Client;

public class ClientMapper {
    public static Client toEntity(ClientRequest request){
            Client c = new Client();
            c.setName(request.name());
            c.setAge(request.age());
            c.setTelephone(request.telephone());
            c.setAddress(request.address());
            c.setGender(request.gender());

            return c;
    }

    public static ClientResponse toDTO(Client client)
    {
        return new ClientResponse(
        client.getId(),
        client.getName(),
        client.getAge(),
        client.getGender(),
        client.getTelephone(),
        client.getAddress()
);
    }
}
