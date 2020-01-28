package com.centricsoftware.assignment.persistence.repository;

import com.centricsoftware.assignment.persistence.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String> {
    public List<Product> findAllPagesByCategory(String category, Pageable pageable);
}