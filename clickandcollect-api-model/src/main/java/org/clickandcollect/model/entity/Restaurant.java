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
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull
    private String email;
    @Size(min = 6)
    @NotNull
    private String password;
    @Column(length = 100)
    @NotNull
    private String name;
    @Column(length = 100)
    private String typeCuisine;
    private String description;
    private String formattedAddress;
    private String latitude;
    private String longitude;
    private String photo;

    @OneToMany(
            mappedBy = "restaurant",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Product> products = new ArrayList<>();

    @OneToMany(
            mappedBy = "restaurant",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Menu> menus = new ArrayList<>();

    @OneToMany(
            mappedBy = "restaurant",
            orphanRemoval = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<BusinessHour> businessHours = new ArrayList<>();

    public void addBusinessHour(BusinessHour businessHour) {
        this.businessHours.add(businessHour);
        businessHour.setRestaurant(this);
    }

    public void addAllBusinessHours(List<BusinessHour> businessHours) {
        this.businessHours.clear();
        businessHours.forEach(this::addBusinessHour);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                '}';
    }
}
