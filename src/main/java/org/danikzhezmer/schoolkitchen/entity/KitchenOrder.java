package org.danikzhezmer.schoolkitchen.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;

@Entity
@Table(name = "kitchen_order")
public class KitchenOrder {


    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private SchoolGroup group;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Instant creationDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Instant orderDateTo;


    public KitchenOrder(Long id, SchoolGroup group, Instant creationDate, Instant orderDateTo, String status) {
        this.id = id;
        this.group = group;
        this.creationDate = creationDate;
        this.orderDateTo = orderDateTo;

    }

    public KitchenOrder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SchoolGroup getGroup() {
        return group;
    }

    public void setGroup(SchoolGroup group) {
        this.group = group;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getOrderDateTo() {
        return orderDateTo;
    }

    public void setOrderDateTo(Instant orderDateTo) {
        this.orderDateTo = orderDateTo;
    }


    @Override
    public String toString() {
        return "KitchenOrder{" +
                "id=" + id +
                ", group=" + group +
                ", creationDate=" + creationDate +
                ", orderDateTo=" + orderDateTo +
                '}';
    }
}
