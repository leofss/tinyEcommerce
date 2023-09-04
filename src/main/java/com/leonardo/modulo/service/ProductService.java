package com.leonardo.modulo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leonardo.modulo.dto.ProductDto;
import com.leonardo.modulo.entity.Product;
import com.leonardo.modulo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProductsFromApi(){
        final String uri = "https://dummyjson.com/products/search?q=phone";

        RestTemplate restTemplate = new RestTemplate();
        String jsonResponse = restTemplate.getForObject(uri, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode root = objectMapper.readTree(jsonResponse);
            JsonNode productsNode = root.get("products");

            List<Product> products = new ArrayList<>();
            if (productsNode.isArray()) {
                for (JsonNode productNode : productsNode) {
                    Long productId = productNode.path("id").asLong();
                    Optional<Product> existingProduct = productRepository.findByProductId(productId);
                    if (existingProduct.isPresent()) {
                        log.info("Product with productId {} already exists. Skipping.", productId);
                    } else {
                        Product product = objectMapper.treeToValue(productNode, Product.class);
                        ProductDto productDto = product.productToDto();
                        Product productEntity = new Product(productDto, productDto.id());
                        productEntity.setProductId(productId);
                        productRepository.save(productEntity);
                        products.add(product);
                    }

                }
            }

            return products;

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<ProductDto> getAll(){
        getProductsFromApi();
        return productRepository.findAll().stream()
                .map(p -> p.productToDto())
                .toList();
    }

    public ProductDto getProductByProductId(Long productId){
        Optional<Product> productDtoOptional = this.productRepository.findByProductId(productId);
        return productDtoOptional.get().productToDto();
    }

}
