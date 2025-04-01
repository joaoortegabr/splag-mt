package com.project.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.models.mappers.LotacaoMapper;
import com.project.models.mappers.ServidorEfetivoMapper;
import com.project.models.mappers.ServidorTemporarioMapper;
import com.project.models.mappers.UnidadeMapper;

@Configuration
public class MapStructConfig {

	@Bean
    LotacaoMapper lotacaoMapper() {
        return Mappers.getMapper(LotacaoMapper.class);
    }
	
	@Bean
    ServidorEfetivoMapper servidorEfetivoMapper() {
        return Mappers.getMapper(ServidorEfetivoMapper.class);
    }
	
	@Bean
    ServidorTemporarioMapper servidorTemporarioMapper() {
        return Mappers.getMapper(ServidorTemporarioMapper.class);
    }
	
	@Bean
    UnidadeMapper unidadeMapper() {
        return Mappers.getMapper(UnidadeMapper.class);
    }
	
}
