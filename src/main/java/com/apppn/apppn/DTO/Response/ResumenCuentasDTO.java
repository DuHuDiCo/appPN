package com.apppn.apppn.DTO.Response;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResumenCuentasDTO {



    Map<String, Map<String, Object>> map = new HashMap<>();
    public ResumenCuentasDTO() {}

   

    public Map<String, Map<String, Object>> getMap() {
        return map;
    }

    public void setMap(Map<String, Map<String, Object>> map) {
        this.map = map;
    }

  

   
}
