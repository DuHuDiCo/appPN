package com.apppn.apppn.DTO.Request;

public class ClasificacionDTO {

    private String clasificacionProducto;
    private Boolean isFleteObligatorio;
    public ClasificacionDTO() {
    }
    public String getClasificacionProducto() {
        return clasificacionProducto;
    }
    public void setClasificacionProducto(String clasificacionProducto) {
        this.clasificacionProducto = clasificacionProducto;
    }
    public Boolean getIsFleteObligatorio() {
        return isFleteObligatorio;
    }
    public void setIsFleteObligatorio(Boolean isFleteObligatorio) {
        this.isFleteObligatorio = isFleteObligatorio;
    }

    

}
