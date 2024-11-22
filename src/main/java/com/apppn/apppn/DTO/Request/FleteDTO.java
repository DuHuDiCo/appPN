package com.apppn.apppn.DTO.Request;

import java.util.ArrayList;
import java.util.List;

public class FleteDTO {


    private Double flete;
    private List<Long> idProductos = new ArrayList<>();
    public FleteDTO() {
    }
    public Double getFlete() {
        return flete;
    }
    public void setFlete(Double flete) {
        this.flete = flete;
    }
    public List<Long> getIdProductos() {
        return idProductos;
    }
    public void setIdProductos(List<Long> idProductos) {
        this.idProductos = idProductos;
    }

    

}
