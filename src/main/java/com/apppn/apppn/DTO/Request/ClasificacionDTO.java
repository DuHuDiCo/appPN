package com.apppn.apppn.DTO.Request;

public class ClasificacionDTO {

    private String clasificacionProducto;
    private Double contado;
    private Double credito;
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
    public Double getContado() {
        return contado;
    }
    public void setContado(Double contado) {
        this.contado = contado;
    }
    public Double getCredito() {
        return credito;
    }
    public void setCredito(Double credito) {
        this.credito = credito;
    }

    
    

}
