package org.danikzhezmer.schoolkitchen.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kitchen_order_product")
public class KitchenOrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "Kitchen_order_id", referencedColumnName = "id")
    private KitchenOrder kitchenOrder;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private String measure;

    private int qty;


    public KitchenOrderProduct() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public KitchenOrder getKitchenOrder() {
        return kitchenOrder;
    }

    public void setKitchenOrder(KitchenOrder kitchenOrder) {
        this.kitchenOrder = kitchenOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public KitchenOrderProduct(Long id, KitchenOrder kitchenOrder, Product product, String measure, int qty) {
        this.id = id;
        this.kitchenOrder = kitchenOrder;
        this.product = product;
        this.measure = measure;
        this.qty = qty;
    }
}
