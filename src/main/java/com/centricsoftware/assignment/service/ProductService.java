package com.centricsoftware.assignment.service;

import com.centricsoftware.assignment.controller.model.CreateProductCommand;
import com.centricsoftware.assignment.persistence.model.Product;
import com.centricsoftware.assignment.persistence.model.Tag;
import com.centricsoftware.assignment.persistence.repository.ProductRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(CreateProductCommand productCommand) {

        if(productCommand == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category parameter is required.");
        }


        Product product = new Product(
                UUID.randomUUID().toString(),
                productCommand.getName(),
                productCommand.getDescription(),
                productCommand.getBrand(),
                productCommand.getTags().stream().map(Tag::new).collect(Collectors.toList()),
                productCommand.getCategory(),
                LocalDateTime.now()
        );

        product.getTags().forEach(t -> t.setProduct(product));

        return this.productRepository.save(product);
    }

    @Override
    public List<Product> findAll(String category, Pageable pageable) {

        if(category == null || category.isEmpty() || pageable == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Category et pageable parameter are required.");
        }

        return this.productRepository.findAllPagesByCategory(category, pageable);
    }
}
