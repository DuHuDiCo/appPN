package com.apppn.apppn.DTO.Response;

import java.util.ArrayList;
import java.util.List;

public class ResumenCuentasDTO {



    List<FacturacionDTOResponse> facturacionDTO = new ArrayList<>();

    public ResumenCuentasDTO() {
    }

    public List<FacturacionDTOResponse> getFacturacionDTO() {
        return facturacionDTO;
    }

    public void setFacturacionDTO(List<FacturacionDTOResponse> facturacionDTO) {
        this.facturacionDTO = facturacionDTO;
    }

    

  

   
}
