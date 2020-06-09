package org.clickandcollect.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ProductInMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    private Product product;
    @ManyToOne
    private MenuCourse menuCourse;
    @Min(0)
    private double extraCost;
}
