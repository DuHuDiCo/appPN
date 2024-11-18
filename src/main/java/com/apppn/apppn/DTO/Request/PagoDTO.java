package com.apppn.apppn.DTO.Request;

import org.springframework.web.multipart.MultipartFile;

public class PagoDTO {

    private MultipartFile archivo;

    private Double totalPago;

    public PagoDTO() {
    }

    public MultipartFile getArchivo() {
        return archivo;
    }

    public void setArchivo(MultipartFile archivo) {
        this.archivo = archivo;
    }

    public Double getTotalPago() {
        return totalPago;
    }

    public void setTotalPago(Double totalPago) {
        this.totalPago = totalPago;
    }

    

}
