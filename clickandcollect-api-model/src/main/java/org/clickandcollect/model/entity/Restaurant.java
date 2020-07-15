package org.clickandcollect.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant implements UserDetails {
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
    @JsonIgnore
    private Point location;
    private String photo;
    @Transient
    private Double distance;

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

    @Builder.Default
    private String roles = "";
    @Builder.Default
    private boolean expired = false;
    @Builder.Default
    private boolean locked = false;
    @Builder.Default
    private boolean enabled = true;

    public boolean hasRole(String role){
        return this.getRoleList().contains(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        this.getRoleList().forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
            authorities.add(authority);
        });
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    private List<String> getRoleList(){
        if (this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !expired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }


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
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", typeCuisine='" + typeCuisine + '\'' +
                ", description='" + description + '\'' +
                ", formattedAddress='" + formattedAddress + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
