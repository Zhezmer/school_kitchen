package org.danikzhezmer.schoolkitchen.entity;

import jakarta.persistence.*;

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

    private Instant creationDate;
    private Instant orderDateTo;

    private String status;

    public KitchenOrder(Long id, SchoolGroup group, Instant creationDate, Instant orderDateTo, String status) {
        this.id = id;
        this.group = group;
        this.creationDate = creationDate;
        this.orderDateTo = orderDateTo;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "KitchenOrder{" +
                "id=" + id +
                ", group=" + group +
                ", creationDate=" + creationDate +
                ", orderDateTo=" + orderDateTo +
                ", status='" + status + '\'' +
                '}';
    }
}
