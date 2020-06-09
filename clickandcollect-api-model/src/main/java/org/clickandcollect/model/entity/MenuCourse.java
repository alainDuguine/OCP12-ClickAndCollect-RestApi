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
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MenuCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    private Menu menu;
    @ManyToOne()
    private Category category;

    @OneToMany(
            mappedBy = "menuCourse",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ProductInMenu> productsInMenu = new ArrayList<>();
}
