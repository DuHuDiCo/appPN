package com.apppn.apppn.DTO.Request;

import java.util.Date;

public class InventoryDTO {

    private Date dateInventory;
    private Double totalInventoryValue;
    private Double totalCostValue;
    private Integer quantity;
    private Long userId;
    
    public InventoryDTO() {
    }
    public Date getDateInventory() {
        return dateInventory;
    }
    public void setDateInventory(Date dateInventory) {
        this.dateInventory = dateInventory;
    }
    public Double getTotalInventoryValue() {
        return totalInventoryValue;
    }
    public void setTotalInventoryValue(Double totalInventoryValue) {
        this.totalInventoryValue = totalInventoryValue;
    }
    public Double getTotalCostValue() {
        return totalCostValue;
    }
    public void setTotalCostValue(Double totalCostValue) {
        this.totalCostValue = totalCostValue;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }


    
    

}
