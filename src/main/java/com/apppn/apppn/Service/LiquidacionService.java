package com.apppn.apppn.Service;

import org.springframework.http.ResponseEntity;

import com.apppn.apppn.DTO.Request.LiquidacionDTO;

public interface LiquidacionService {


    public ResponseEntity<?> saveLiquidacion(LiquidacionDTO liquidacionDTO);


    public ResponseEntity<?> getLiquidacionesByUser(Long idUser);

}
