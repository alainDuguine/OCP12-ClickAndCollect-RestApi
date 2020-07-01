package org.clickandcollect.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

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

    @Override
    public String toString() {
        return "ProductInCourse{" +
                "id=" + id +
                ", extraCost=" + extraCost +
                ", product=" + product +
                '}';
    }
}
