package dev.joaountura.payments_project.product.mapper;


import dev.joaountura.payments_project.product.models.Product;
import dev.joaountura.payments_project.product.models.ProductCreateDTO;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static Product toProductCreate(ProductCreateDTO productCreateDTODto){

        return Product.builder().name(productCreateDTODto.name()).value(productCreateDTODto.value()).build();

    }



}
