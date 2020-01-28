package com.centricsoftware.assignment.controller;

import com.centricsoftware.assignment.controller.model.CreateProductCommand;
import com.centricsoftware.assignment.persistence.model.Product;
import com.centricsoftware.assignment.service.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.centricsoftware.assignment.persistence.model.Tag;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class ProductControllerTest {

    private IProductService productService;
    private ProductController productController;

    @BeforeEach
    public void init(){
        this.productService =  Mockito.mock(IProductService.class);
        this.productController = new ProductController(productService);
    }

    @Test
    void shouldCallServiceCreate() {
        List<String> tags = Arrays.asList(
               "red",
               "shirt",
               "slimfit"
        );

        CreateProductCommand createProductCommand = new CreateProductCommand();

        createProductCommand.setName("coco");
        createProductCommand.setDescription("description");
        createProductCommand.setBrand("Nike");
        createProductCommand.setTags(tags);
        createProductCommand.setCategory("short");

        Product product = new Product();
        product.setTags(tags.stream().map(Tag::new).collect(Collectors.toList()));

        Mockito.when(productService.create(createProductCommand)).thenReturn(product);

        productController.create(createProductCommand);
        Mockito.verify(productService, Mockito.times(1)).create(Mockito.any());
    }

    @Test
    void ShouldCallServiceFindAll() {
        Mockito.when(productService.findAll(Mockito.anyString(), Mockito.any())).thenReturn(Collections.emptyList());
        productController.getAllProducts("short", 0, 20);
        Mockito.verify(productService, Mockito.times(1)).findAll(Mockito.anyString(), Mockito.any());
    }
}