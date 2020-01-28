package com.centricsoftware.assignment.service;

import com.centricsoftware.assignment.controller.model.CreateProductCommand;
import com.centricsoftware.assignment.persistence.model.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    Product create(CreateProductCommand productCommand);
    List<Product> findAll(String category, Pageable pageable);
}
