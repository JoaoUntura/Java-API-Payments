package dev.joaountura.payments_project.product.controllers;
import dev.joaountura.payments_project.product.mapper.ProductMapper;
import dev.joaountura.payments_project.product.models.Product;
import dev.joaountura.payments_project.product.models.ProductCreateDTO;
import dev.joaountura.payments_project.product.services.ProductServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@Tag(name = "Products", description = "Products related operations")
public class ProductController {

    private final ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @PostMapping
    @Operation(summary = "Create new product")
    private ResponseEntity<Product> postController(@RequestBody ProductCreateDTO productCreateDTO){

       Product productCreated = productServices.createProduct(ProductMapper.toProductCreate(productCreateDTO));

       return ResponseEntity.status(HttpStatus.CREATED).body(productCreated);


    }

    @GetMapping
    @Operation(summary = "Get all products")
    private ResponseEntity<List<Product>> getController(){
        List<Product> products = productServices.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

}
