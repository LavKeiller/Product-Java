package com.fatec.itu.product.mappers;

import com.fatec.itu.product.dtos.ClientRequest;
import com.fatec.itu.product.dtos.ProductRequest;
import com.fatec.itu.product.dtos.ProductResponse;
import com.fatec.itu.product.entities.Product;

public class ProductMapper {

    public static Product toEntity(ProductRequest product){
            Product p = new Product();
            p.setDescription(product.description());
            p.setName(product.name());
            p.setPrice(product.price());

            return p;
    }

    public static ProductResponse toDTO(Product product)
    {
        return new ProductResponse(
            product.getId(),
            product.getName(),
            product.getDescription()
        );
    }
}
