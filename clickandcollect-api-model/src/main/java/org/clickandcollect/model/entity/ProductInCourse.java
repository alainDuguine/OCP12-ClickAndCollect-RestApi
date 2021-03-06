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
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductInCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(0)
    private Double extraCost;

    @ManyToOne()
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private MenuCourse menuCourse;

    @OneToMany(
            mappedBy = "productInCourse",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<SelectedProduct> selectedProducts = new ArrayList<>();

    public void addSelectedProduct(SelectedProduct product) {
        this.selectedProducts.add(product);
        product.setProductInCourse(this);
    }

    @Override
    public String toString() {
        return "ProductInCourse{" +
                "id=" + id +
                ", extraCost=" + extraCost +
                ", product=" + product +
                '}';
    }
}
