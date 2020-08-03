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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @Email
    private String email;
    @Size(min=10,max=10)
    private String phoneNumber;
    @NotNull
    private LocalDateTime pickupDateTime;

    @OneToMany(
            mappedBy = "clientOrder",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<MenuOrder> menuOrders = new ArrayList<>();

    @OneToMany(
            mappedBy = "clientOrder",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    List<ProductOrder> productOrders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Restaurant restaurant;

    public void addMenuOrder(MenuOrder menuOrder) {
        this.menuOrders.add(menuOrder);
        menuOrder.setClientOrder(this);
    }

    public void addProductOrder(ProductOrder productOrder) {
        this.productOrders.add(productOrder);
        productOrder.setClientOrder(this);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", pickupDateTime=" + pickupDateTime +
                '}';
    }
}
