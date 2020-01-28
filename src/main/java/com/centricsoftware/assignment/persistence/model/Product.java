package com.centricsoftware.assignment.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product implements Serializable {

    @Id
    private String id;
    private String name;
    private String description;
    private String brand;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "product", cascade = CascadeType.ALL)
    private List<Tag> tags;
    private String category;
    private LocalDateTime createdAt;
}
