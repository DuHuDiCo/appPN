package com.apppn.apppn.Utils;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.apppn.apppn.DTO.Request.AplicarPagoDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StringToAplicarPagoDTOListConverter implements Converter<String,List<AplicarPagoDTO>>{


    private final ObjectMapper objectMapper;

    public StringToAplicarPagoDTOListConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }



    @Override
    public List<AplicarPagoDTO> convert(String source) {
        try {
            return objectMapper.readValue(source, new TypeReference<List<AplicarPagoDTO>>() {});
        } catch (Exception e) {
            throw new IllegalArgumentException("Error al convertir JSON a List<AplicarPagoDTO>", e);
        }
    }

}
