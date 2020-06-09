package org.clickandcollect.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
    private List<ProductInMenu> productsInMenu = new ArrayList<>();

    @ManyToOne()
    @NotNull
    private Menu menu;
    @ManyToOne()
    @NotNull
    private Category category;

    public void addProductInMenu(ProductInMenu productInMenu) {
        this.productsInMenu.add(productInMenu);
        productInMenu.setMenuCourse(this);
    }

    public void removeProduct(ProductInMenu productInMenu) {
        this.productsInMenu.remove(productInMenu);
        productInMenu.setMenuCourse(null);
    }

}
