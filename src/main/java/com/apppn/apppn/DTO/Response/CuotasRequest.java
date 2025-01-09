package com.apppn.apppn.DTO.Response;

public class CuotasRequest {

    private Long idCuota;
    private Double valor;
    
    private Long idFacturacion;
    public Long getIdFacturacion() {
        return idFacturacion;
    }
    public void setIdFacturacion(Long idFacturacion) {
        this.idFacturacion = idFacturacion;
    }
    public CuotasRequest() {
    }
    public Long getIdCuota() {
        return idCuota;
    }
    public void setIdCuota(Long idCuota) {
        this.idCuota = idCuota;
    }
    public Double getValor() {
        return valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
 
    
    

    

}
