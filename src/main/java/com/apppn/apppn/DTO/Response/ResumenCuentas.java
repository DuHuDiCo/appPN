package com.apppn.apppn.DTO.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ResumenCuentas {

    private List<FacturacionDTOResponse> facturacionDTOResponses = new ArrayList<>();

    List<Map<String, Object>> map = new ArrayList<>();

    public ResumenCuentas() {
    }

    public List<FacturacionDTOResponse> getFacturacionDTOResponses() {
        return facturacionDTOResponses;
    }

    public void setFacturacionDTOResponses(List<FacturacionDTOResponse> facturacionDTOResponses) {
        this.facturacionDTOResponses = facturacionDTOResponses;
    }

    public List<Map<String, Object>> getMap() {
        return map;
    }

    public void setMap(List<Map<String, Object>> map) {
        this.map = map;
    }



    

}
