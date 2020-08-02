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
import javax.persistence.JoinColumn;
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="client_order_id")
    private Order order;

    @NotNull
    @OneToMany(
            mappedBy = "menuOrder",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<SelectedProduct> selectedProducts = new ArrayList<>();

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
