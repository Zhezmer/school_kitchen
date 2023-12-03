package org.danikzhezmer.schoolkitchen.entity;

public class Statistic {

   private String groupName;
   private String product;
   private  int totalQuantity;
   private String measure;

    public Statistic(String groupName, String product, int totalQuantity, String measure) {
        this.groupName = groupName;
        this.product = product;
        this.totalQuantity = totalQuantity;
        this.measure = measure;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }
}
