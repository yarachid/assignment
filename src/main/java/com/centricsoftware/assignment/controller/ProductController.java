package com.centricsoftware.assignment.controller;

import com.centricsoftware.assignment.controller.model.CreateProductCommand;
import com.centricsoftware.assignment.controller.model.ProductView;
import com.centricsoftware.assignment.service.IProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/products")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService iProductService) {
        this.productService = iProductService;
    }

    @PostMapping
    public ProductView create(@RequestBody @Valid CreateProductCommand createProductCommand) {
        return new ProductView(this.productService.create(createProductCommand));
    }

    @GetMapping
    public List<ProductView> getAllProducts(@RequestParam(required = true) String category,
                                            @RequestParam(value = "page", defaultValue = "0") int page,
                                            @RequestParam(value = "limit", defaultValue = "20") int limit) {
        return this.productService
                .findAll(category, PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC,"createdAt")))
                .stream()
                .map(ProductView::new)
                .collect(Collectors.toList());
    }
}
