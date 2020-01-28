package com.centricsoftware.assignment.integration;

import com.centricsoftware.assignment.controller.model.CreateProductCommand;
import com.centricsoftware.assignment.persistence.model.Product;
import com.centricsoftware.assignment.persistence.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EndpointTest {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    ProductRepository productRepository;

    @Before
    @AfterEach
    public void init(){
        this.productRepository.deleteAll();
    }

    @Test
    public void shouldCreateAndRetrieveProduct(){

        CreateProductCommand createProductCommand = new CreateProductCommand();
        createProductCommand.setName("name");
        createProductCommand.setBrand("brand");
        createProductCommand.setCategory("cloth");
        createProductCommand.setDescription("description");
        createProductCommand.setTags(Arrays.asList("tag0", "tag1", "tag2"));

        this.webTestClient
                .post()
                .uri("/v1/products")
                .bodyValue(createProductCommand)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Product.class);


        this.webTestClient
                .post()
                .uri("/v1/products")
                .bodyValue(createProductCommand)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Product.class);

        this.webTestClient
                .get()
                .uri("/v1/products?category=cloth")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Product.class)
                .hasSize(2);
    }

    @Test
    public void shouldReturnBadRequestOnMissingCommandPropertie() {
        CreateProductCommand createProductCommand = new CreateProductCommand();
        createProductCommand.setName("name");
        createProductCommand.setBrand(null);
        createProductCommand.setCategory(null);
        createProductCommand.setDescription("description");
        createProductCommand.setTags(Arrays.asList("tag0", "tag1", "tag2"));

        this.webTestClient
                .post()
                .uri("/v1/products")
                .bodyValue(createProductCommand)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isBadRequest();

        this.webTestClient
                .get()
                .uri("/v1/products?category=cloth")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Product.class)
                .hasSize(0);
    }

    @Test
    public void shouldReturnBadRequestOnEmptyCategoryParameter(){
        this.webTestClient
                .get()
                .uri("/v1/products")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isBadRequest();
    }
}
