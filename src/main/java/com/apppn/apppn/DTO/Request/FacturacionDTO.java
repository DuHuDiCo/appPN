package com.apppn.apppn.DTO.Request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FacturacionDTO {


    private Long idInventario;

    

    private List<FacturacionProductosDTO> productos = new ArrayList<>();


    public FacturacionDTO() {
    }


    public Long getIdInventario() {
        return idInventario;
    }


    public void setIdInventario(Long idInventario) {
        this.idInventario = idInventario;
    }


    public List<FacturacionProductosDTO> getProductos() {
        return productos;
    }


    public void setProductos(List<FacturacionProductosDTO> productos) {
        this.productos = productos;
    }


   


  
    

    


}
