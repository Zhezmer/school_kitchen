package org.danikzhezmer.schoolkitchen.dto;

import java.time.LocalDate;
import java.util.List;

public class KitchenOrderDto {

    private Long orderId;

    private Long groupId;

    private LocalDate creationDate;

    private LocalDate orderDateTo;

    private List<KitchenOrderItemDto> items;

    public KitchenOrderDto() {
    }

    public KitchenOrderDto(Long orderId,
                           Long groupId,
                           LocalDate creationDate,
                           LocalDate orderDateTo,
                           List<KitchenOrderItemDto> items) {
        this.orderId = orderId;
        this.groupId = groupId;
        this.creationDate = creationDate;
        this.orderDateTo = orderDateTo;
        this.items = items;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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

    public List<KitchenOrderItemDto> getItems() {
        return items;
    }

    public void setItems(List<KitchenOrderItemDto> items) {
        this.items = items;
    }
}