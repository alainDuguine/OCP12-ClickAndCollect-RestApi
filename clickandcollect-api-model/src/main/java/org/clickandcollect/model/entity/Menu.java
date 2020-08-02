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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_id","name"})
)
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    @NotNull
    private String name;
    private String description;
    @Min(0)
    @NotNull
    private Double price;

    @OneToMany(
            mappedBy = "menu",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Builder.Default
    private List<MenuCourse> menuCourses = new ArrayList<>();

    @OneToMany(
            mappedBy = "menu",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    @Builder.Default
    private List<MenuOrder> menuOrders = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    public void addCourse(MenuCourse course) {
        this.menuCourses.add(course);
        course.setMenu(this);
    }

    public void removeCourse(MenuCourse course) {
        this.menuCourses.remove(course);
        course.setMenu(null);
    }

    public void addOrder(MenuOrder order) {
        this.menuOrders.add(order);
        order.setMenu(this);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", menuCourses=" + menuCourses +
                ", restaurant=" + restaurant +
                '}';
    }
}
