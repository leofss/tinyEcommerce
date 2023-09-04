package com.leonardo.modulo.controller;

import com.leonardo.modulo.dto.ProductDto;
import com.leonardo.modulo.entity.Product;
import com.leonardo.modulo.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    private ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ProductDto getProductByProductId(@PathVariable("id") Long id){
        return productService.getProductByProductId(id);
    }
//    @GetMapping
//    public List<ProductDto> getAll(){
//        productService.fetchAndSaveProductsFromExternalAPI();
//    }
}
