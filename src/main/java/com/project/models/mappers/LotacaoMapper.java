package com.project.models.mappers;

import org.mapstruct.Mapper;

import com.project.entities.Lotacao;
import com.project.models.dtos.LotacaoRequest;
import com.project.models.dtos.LotacaoResponse;

@Mapper(componentModel = "spring")
public interface LotacaoMapper {
    
	Lotacao toLotacao(LotacaoRequest request);
    
	LotacaoResponse toLotacaoResponse(Lotacao lotacao);

}
