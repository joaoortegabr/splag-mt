package com.project.models.mappers;

import org.mapstruct.Mapper;

import com.project.entities.ServidorTemporario;
import com.project.models.dtos.ServidorTemporarioRequest;
import com.project.models.dtos.ServidorTemporarioResponse;

@Mapper(componentModel = "spring")
public interface ServidorTemporarioMapper {
    
	ServidorTemporario toServidorTemporario(ServidorTemporarioRequest request);
    
	ServidorTemporarioResponse toServidorTemporarioResponse(ServidorTemporario servidorTemporario);

}
