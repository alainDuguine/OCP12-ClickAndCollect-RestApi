package org.clickandcollect.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(
            mappedBy = "menuCourse",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @Builder.Default
    private List<ProductInCourse> productsInCourse = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Category category;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    private Menu menu;

    public void addProductInMenu(ProductInCourse productInCourse) {
        this.productsInCourse.add(productInCourse);
        productInCourse.setMenuCourse(this);
    }

    public void removeProduct(ProductInCourse productInCourse) {
        this.productsInCourse.remove(productInCourse);
        productInCourse.setMenuCourse(null);
    }

    @Override
    public String toString() {
        return "MenuCourse{" +
                "id=" + id +
                ", productsInMenu=" + productsInCourse +
                ", category=" + category +
                '}';
    }
}
