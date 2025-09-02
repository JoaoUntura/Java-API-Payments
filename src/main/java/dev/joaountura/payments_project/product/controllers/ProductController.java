package dev.joaountura.payments_project.product.controllers;
import dev.joaountura.payments_project.product.mapper.ProductMapper;
import dev.joaountura.payments_project.product.models.Product;
import dev.joaountura.payments_project.product.models.ProductCreateDTO;
import dev.joaountura.payments_project.product.services.ProductServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @PostMapping
    private ResponseEntity<Product> postController(@RequestBody ProductCreateDTO productCreateDTO){

       Product productCreated = productServices.createProduct(ProductMapper.toProductCreate(productCreateDTO));

       return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);


    }

    @GetMapping
    private ResponseEntity<List<Product>> getController(){
        List<Product> products = productServices.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

}
