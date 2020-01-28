package com.centricsoftware.assignment.service;

import com.centricsoftware.assignment.controller.model.CreateProductCommand;
import com.centricsoftware.assignment.persistence.model.Product;
import com.centricsoftware.assignment.persistence.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    private IProductService productService;
    private ProductRepository productRepository;
    private final List<Product> products = Arrays.asList(new Product(
            UUID.randomUUID().toString(),
            "name",
            "description",
            "brand",
            Collections.emptyList(),
            "apparel",
            LocalDateTime.now()
    ), new Product(
            UUID.randomUUID().toString(),
            "name2",
            "description2",
            "brand2",
            Collections.emptyList(),
            "apparel",
            LocalDateTime.now()
    ), new Product(
            UUID.randomUUID().toString(),
            "name3",
            "description2",
            "brand2",
            Collections.emptyList(),
            "apparel",
            LocalDateTime.now()
    ));

    @BeforeEach
    public void init(){
        this.productRepository = Mockito.mock(ProductRepository.class);
        this.productService = new ProductService(productRepository);
    }

    @Test
    void shouldFindAllProduct() {
        productService.findAll("toto", Pageable.unpaged());
        Mockito.verify(productRepository, Mockito.times(1)).findAllPagesByCategory(Mockito.anyString(), Mockito.any());
        assertEquals(0, productService.findAll("apparel", PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC,"createdAt"))).size());
    }

    @Test
    void shouldCreateProduct(){
        CreateProductCommand createProductCommand = new CreateProductCommand();
        createProductCommand.setName("name");
        createProductCommand.setBrand("brand");
        createProductCommand.setCategory("category");
        createProductCommand.setDescription("description");
        createProductCommand.setTags(Collections.emptyList());

        productService.create(createProductCommand);
        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any());
    }


    @Test
    void shouldThrowBadRequestExceptionOnEmptyCommand(){
        assertThrows(ResponseStatusException.class,  () -> productService.create(null));
    }

    @Test
    void shouldThrowBadRequestExceptionOnFindAllWithEmptyValues(){
        assertThrows(ResponseStatusException.class, () -> productService.findAll("", Pageable.unpaged()));
        assertThrows(ResponseStatusException.class, () -> productService.findAll(null, Pageable.unpaged()));
        assertThrows(ResponseStatusException.class, () -> productService.findAll("test", null));
    }

}