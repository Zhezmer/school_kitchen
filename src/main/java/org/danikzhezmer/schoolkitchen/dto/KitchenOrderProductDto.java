package org.danikzhezmer.schoolkitchen.dto;

public class KitchenOrderProductDto {

    private Long Id;
    private Long kitchenOrderId;
    private Long productId;

    private String productName;

    private String measure;

    private int qty;

    public KitchenOrderProductDto() {
    }

    public KitchenOrderProductDto(Long id, Long kitchenOrderId, Long productId, String productName, String measure, int qty) {
        Id = id;
        this.kitchenOrderId = kitchenOrderId;
        this.productId = productId;
        this.productName = productName;
        this.measure = measure;
        this.qty = qty;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getKitchenOrderId() {
        return kitchenOrderId;
    }

    public void setKitchenOrderId(Long kitchenOrderId) {
        this.kitchenOrderId = kitchenOrderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
}
