package com.fatec.itu.product.dtos;

public record ClientRequest(
    Long id,
    String name,
    int age,
    String gender,
    int telephone,
    String address
) {
    
}