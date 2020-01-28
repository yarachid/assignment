package com.centricsoftware.assignment.controller.model;

import com.centricsoftware.assignment.persistence.model.Product;
import com.centricsoftware.assignment.persistence.model.Tag;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductView {
    private String id;
    private String name;
    private String description;
    private String brand;
    private List<String> tags;
    private String category;
    private LocalDateTime createdAt;

    public ProductView(Product product){
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.brand = product.getBrand();
        this.tags = product.getTags().stream()
                .map(Tag::getName)
                .collect(Collectors.toList());
        this.category = product.getCategory();
        this.createdAt = product.getCreatedAt();
    }
}
