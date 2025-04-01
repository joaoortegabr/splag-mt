package com.project.mappers;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.project.entities.Unidade;
import com.project.models.dtos.UnidadeRequest;
import com.project.models.dtos.UnidadeResponse;
import com.project.models.mappers.UnidadeMapperImpl;

public class UnidadeMapperTest {

    private UnidadeMapperImpl unidadeMapper;

    @BeforeEach
    void setUp() {
        unidadeMapper = new UnidadeMapperImpl();
    }
    
    @Test
    @DisplayName("Check if a Unidade mapper returns null if UnidadeRequest is null")
    void shouldReturnNullWhenUnidadeRequestIsNull() {
        UnidadeRequest unidadeRequest = null;

        Unidade result = unidadeMapper.toUnidade(unidadeRequest);

        assertNull(result, "O resultado deve ser null quando o UnidadeRequest for null");
    }
    
    @Test
    @DisplayName("Check if a UnidadeResponse mapper returns null if Unidade is null")
    void shouldReturnNullWhenUnidadeIsNull() {
    	Unidade unidade = null;

        UnidadeResponse result = unidadeMapper.toUnidadeResponse(unidade);

        assertNull(result, "O resultado deve ser null quando o Unidade for null");
    }
    
}
