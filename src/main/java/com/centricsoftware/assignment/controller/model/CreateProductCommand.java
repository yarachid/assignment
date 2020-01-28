package com.centricsoftware.assignment.controller.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CreateProductCommand {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotEmpty
    private String brand;
    @NotEmpty
    private List<String> tags;
    @NotEmpty
    private String category;
}
