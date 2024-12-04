package com.apppn.apppn.DTO.Request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.apppn.apppn.Models.ProductoCompra;

public class InventoryDTO {

    private Date dateInventory;
    private Double totalInventoryValue;
    private Double totalCostValue;
    private Integer quantity;
    

    private List<ProductoCompra> productos = new ArrayList<>();
    
    
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
  

    public List<ProductoCompra> getProductos() {
        return productos;
    }



    public void setProductos(List<ProductoCompra> productos) {
        this.productos = productos;
    }


    
    

}
