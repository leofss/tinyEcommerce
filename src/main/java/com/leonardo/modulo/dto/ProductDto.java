package com.leonardo.modulo.dto;



public record ProductDto(Long id, Long productId, String title, String description, String brand, String category, String thumbnail,
                         double price, double discountPercentage, double rating, int stock) {


    @Override
    public Long id() {
        return id;
    }

    @Override
    public Long productId() {
        return productId;
    }

    @Override
    public String title() {
        return title;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public String brand() {
        return brand;
    }

    @Override
    public String category() {
        return category;
    }

    @Override
    public String thumbnail() {
        return thumbnail;
    }

    @Override
    public double price() {
        return price;
    }

    @Override
    public double discountPercentage() {
        return discountPercentage;
    }

    @Override
    public double rating() {
        return rating;
    }

    @Override
    public int stock() {
        return stock;
    }

}
