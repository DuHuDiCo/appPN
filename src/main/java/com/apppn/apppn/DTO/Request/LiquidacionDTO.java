package com.apppn.apppn.DTO.Request;

import java.util.List;

public class LiquidacionDTO {

    private Long idUser;
    private List<Long> idProductos;

    public LiquidacionDTO() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public List<Long> getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(List<Long> idProductos) {
        this.idProductos = idProductos;
    }

}
