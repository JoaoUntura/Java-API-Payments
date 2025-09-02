package dev.joaountura.payments_project.product.services;

import dev.joaountura.payments_project.product.repository.ProductRepository;
import dev.joaountura.payments_project.product.models.Product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {


    private final ProductRepository productRepository;

    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Product createProduct(Product product){

        return productRepository.save(product);

    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getByIdList(List<Long> idList){
        return productRepository.findAllById(idList);
    }

}
