package org.danikzhezmer.schoolkitchen.dto;

public class KitchenOrderItemDto {

    private Long productId;
    private String measure;
    private int qty;

    public KitchenOrderItemDto(Long productId, String measure, int qty) {
        this.productId = productId;
        this.measure = measure;
        this.qty = qty;
    }

    public KitchenOrderItemDto() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
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