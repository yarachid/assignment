package com.centricsoftware.assignment.controller.model;

import com.centricsoftware.assignment.persistence.model.Product;
import com.centricsoftware.assignment.persistence.model.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductViewTest {


    @Test
    void shouldConstructorProductViewFromProduct(){
        Product product = new Product();
        product.setId(UUID.randomUUID().toString());
        product.setBrand("Nike");
        product.setTags(Arrays.asList(
                new Tag("red"),
                new Tag("shirt"),
                new Tag("slimfit")
        ));

        ProductView productView = new ProductView(product);

        assertEquals(product.getId(), productView.getId());
        assertEquals(product.getBrand(), productView.getBrand());
        assertEquals(product.getTags().get(0).getName(), productView.getTags().get(0));
        assertEquals(product.getTags().get(1).getName(), productView.getTags().get(1));
        assertEquals(product.getTags().get(2).getName(), productView.getTags().get(2));
    }

}