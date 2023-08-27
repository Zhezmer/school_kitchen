package org.danikzhezmer.schoolkitchen.dto;


import java.time.Instant;
import java.time.LocalDate;

public class KitchenOrderDto {

    private String groupName;

    private LocalDate creationDate;

    private LocalDate orderDateTo;

    public String getGroupName() {
        return groupName;
    }

    public KitchenOrderDto(String groupName, LocalDate creationDate, LocalDate orderDateTo) {
        this.groupName = groupName;
        this.creationDate = creationDate;
        this.orderDateTo = orderDateTo;
    }

    public KitchenOrderDto() {
        this.creationDate = LocalDate.now();
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
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

}
