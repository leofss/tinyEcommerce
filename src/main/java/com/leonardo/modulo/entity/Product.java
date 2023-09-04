package com.leonardo.modulo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.leonardo.modulo.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "product")
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "productId")
    private Long productId;
    private String title;
    private String description;
    private String brand;
    private String category;
    private String thumbnail;
    private double price;

    @Column(name = "discountpercentage")
    private double discountPercentage;

    private double rating;
    private int stock;
//
//    @ElementCollection
//    @CollectionTable(name = "product_images", joinColumns = @JoinColumn(name = "product_id"))
//    @Column(name = "image_url")
//    private List<String> images;

    public Product(ProductDto productDto) {
        this.productId = productToDto().productId();
        this.title = productDto.title();
        this.description = productDto.description();
        this.brand = productDto.brand();
        this.category = productDto.category();
        this.thumbnail = productDto.thumbnail();
        this.price = productDto.price();
        this.discountPercentage = productDto.discountPercentage();
        this.rating = productDto.rating();
        this.stock = productDto.stock();
//        this.images = productDto.images();
    }

    public Product(ProductDto productDto, Long id) {
        //this é o Construtor anterior já
        this(productDto);
        this.id = id;
    }

    public ProductDto productToDto(){
        return new ProductDto(this.id, this.productId, this.title, this.description, this.brand, this.category, this.thumbnail, this.price,
                this.discountPercentage, this.rating, this.stock);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +'\'' +
                "product Id=" + productId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", price=" + price +
                ", discountPercentage=" + discountPercentage +
                ", rating=" + rating +
                ", stock=" + stock +
                '}';
    }
}
