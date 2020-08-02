package org.clickandcollect.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClientOrder clientOrder;

    @OneToMany(
            mappedBy = "menuOrder",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    List<SelectedProduct> selectedProducts = new ArrayList<>();

    public void addSelectedProduct(SelectedProduct selectedProduct) {
        this.selectedProducts.add(selectedProduct);
        selectedProduct.setMenuOrder(this);
    }

    @NotNull
    private Integer quantity;

    @Override
    public String toString() {
        return "MenuOrder{" +
                "id=" + id +
                ", menu=" + menu +
                ", quantity=" + quantity +
                '}';
    }
}
