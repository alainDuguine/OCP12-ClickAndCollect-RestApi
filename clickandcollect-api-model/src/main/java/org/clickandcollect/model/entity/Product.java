package org.clickandcollect.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id","name"})
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    @NotNull
    private String name;
    private String description;
    @Min(0)
    @NotNull
    private Double price;
    private String imageUrl;
    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<ProductInMenu> productsInMenu = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Restaurant restaurant;

    public void addProduct(ProductInMenu product) {
        this.productsInMenu.add(product);
        product.setProduct(this);
    }

    public void removeProduct(ProductInMenu product) {
        this.productsInMenu.remove(product);
        product.setProduct(null);
    }

}
