package dev.joaountura.payments_project.product.services;

import dev.joaountura.payments_project.product.repository.ProductRepository;
import dev.joaountura.payments_project.product.models.Product;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {


    private final ProductRepository productRepository;

    public ProductServices(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @CacheEvict(value = "products", key="'allProducts'")
    public Product createProduct(Product product){

        return productRepository.save(product);

    }

    @Cacheable(value = "products", key = "'allProducts'")
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getByIdList(List<Long> idList){
        return productRepository.findAllById(idList);
    }

}
