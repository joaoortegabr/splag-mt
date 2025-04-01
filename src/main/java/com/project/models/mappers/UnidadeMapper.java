package com.project.models.mappers;

import org.mapstruct.Mapper;

import com.project.entities.Unidade;
import com.project.models.dtos.UnidadeRequest;
import com.project.models.dtos.UnidadeResponse;

@Mapper(componentModel = "spring")
public interface UnidadeMapper {
    
	Unidade toUnidade(UnidadeRequest request);
    
	UnidadeResponse toUnidadeResponse(Unidade unidade);

}
