package com.apppn.apppn.Enums;

public enum TipoVentaENUM {


    CONTADO("CONTADO"),
    CREDITO("CREDITO");



    private String dato;

    TipoVentaENUM(String dato) {
        this.dato = dato;
    }

    public String getDato() {
        return dato;
    }
    

}
