package com.apppn.apppn.DTO.Response;

import java.util.ArrayList;
import java.util.List;

public class ResumenCuentasDTO {



    List<CuentaDTO> cuentaDTOs = new ArrayList<>();

    public ResumenCuentasDTO() {
    }

    public List<CuentaDTO> getCuentaDTOs() {
        return cuentaDTOs;
    }

    public void setCuentaDTOs(List<CuentaDTO> cuentaDTOs) {
        this.cuentaDTOs = cuentaDTOs;
    }



    

  

   
}
