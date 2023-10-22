package org.danikzhezmer.schoolkitchen.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.util.List;



@Entity
@Table(name = "kitchen_order")
public class KitchenOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private SchoolGroup group;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate creationDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate orderDateTo;

    @OneToMany(mappedBy = "kitchenOrder", fetch = FetchType.LAZY)
    private List<KitchenOrderItem> kitchenOrderItems;

    public KitchenOrder(Long id, SchoolGroup group, List<KitchenOrderItem> kitchenOrderItems, LocalDate creationDate, LocalDate orderDateTo) {
        this.id = id;
        this.group = group;
        this.creationDate = creationDate;
        this.orderDateTo = orderDateTo;
        this.kitchenOrderItems = kitchenOrderItems;

    }

    public KitchenOrder() {
        this.creationDate = LocalDate.now();
    }

    public List<KitchenOrderItem> getKitchenOrderItems() {
        return kitchenOrderItems;
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getOrderDateTo() {
        return orderDateTo;
    }

    public void setOrderDateTo(LocalDate orderDateTo) {
        this.orderDateTo = orderDateTo;
    }


    @Override
    public String toString() {
        return "KitchenOrder{" +
                "id=" + id +
                ", group=" + group +
                ", creationDate=" + creationDate +
                ", orderDateTo=" + orderDateTo +
                ", kitchenOrderItems=" + kitchenOrderItems +
                '}';
    }
}
