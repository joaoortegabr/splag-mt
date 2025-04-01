package com.project.models.mappers;

import org.mapstruct.Mapper;

import com.project.entities.ServidorEfetivo;
import com.project.models.dtos.ServidorEfetivoRequest;
import com.project.models.dtos.ServidorEfetivoResponse;

@Mapper(componentModel = "spring")
public interface ServidorEfetivoMapper {
    
	ServidorEfetivo toServidorEfetivo(ServidorEfetivoRequest request);
    
	ServidorEfetivoResponse toServidorEfetivoResponse(ServidorEfetivo servidorEfetivo);

}
