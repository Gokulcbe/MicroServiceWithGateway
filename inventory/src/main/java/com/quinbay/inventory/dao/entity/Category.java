package com.quinbay.inventory.dao.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Table(name = "Category")
@Entity
public class Category {
    @Id
    private long categoryId;
    private String categoryName;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL,orphanRemoval = true)
//    @JsonIgnoreProperties("category")
    @JsonManagedReference
    private List<Product> products;
}
