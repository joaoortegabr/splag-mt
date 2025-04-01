package com.project.exceptions;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.project.controllers.UnidadeController;
import com.project.entities.Unidade;
import com.project.models.mocks.UnidadeMock;
import com.project.service.impl.UnidadeServiceImpl;
import com.project.utils.PaginationRequest;

import jakarta.validation.ConstraintViolationException;

@WebMvcTest(UnidadeController.class)
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UnidadeServiceImpl unidadeService;

    @InjectMocks
    private UnidadeController unidadeController;
    
    @InjectMocks
    private UnidadeMock unidadeMock;
    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new UnidadeController(unidadeService))
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Check if ResourceNotFoundException is thrown")
    void shouldReturnResourceNotFoundError() throws Exception {
        when(unidadeService.findById(4L))
        	.thenThrow(new ResourceNotFoundException("4"));
        
        mockMvc.perform(get("/api/v1/unidades/4"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Resource not found"))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("Resource not found: ID 4"))
                .andExpect(jsonPath("$.path").value("/api/v1/unidades/4"));
    }
    
    @Test
    @DisplayName("Check if ConstraintViolationException is thrown")
    void shouldReturnInvalidRequestError() throws Exception {
        when(unidadeService.save(any(Unidade.class)))
        	.thenThrow(new ConstraintViolationException("Invalid request", null));
        
        mockMvc.perform(post("/api/v1/unidades")
                .contentType(MediaType.APPLICATION_JSON)
        		.content("{ \"uniNome\": \"xfhghjgj\" }"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").value("Invalid request"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Invalid request"))
                .andExpect(jsonPath("$.path").value("/api/v1/unidades"));
    }
    
    @Test
    @DisplayName("Check if DataIntegrityViolationException is thrown")
    void shouldReturnDatabaseError() throws Exception {
        when(unidadeService.save(any(Unidade.class)))
        	.thenThrow(new DataIntegrityViolationException("Registro já existe no banco de dados."));

        mockMvc.perform(post("/api/v1/unidades")
                .contentType(MediaType.APPLICATION_JSON)
        		.content("{ \"uniNome\": \"RD_MT-003\" }"));
        		
        mockMvc.perform(post("/api/v1/unidades")
                .contentType(MediaType.APPLICATION_JSON)
				.content("{ \"uniNome\": \"RD_MT-003\" }"))
                .andExpect(status().isConflict())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Database error"))
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.message").value("Registro já existe no banco de dados."))
                .andExpect(jsonPath("$.path").value("/api/v1/unidades"));
    }

    @Test
    @DisplayName("Check if general Exception is thrown")
    void shouldReturnInternalServerError() throws Exception {
        when(unidadeService.findAll(any(PaginationRequest.class)))
    		.thenThrow(new RuntimeException("Not identified error"));
        
        mockMvc.perform(get("/api/v1/unidades"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.error").value("Not identified error"))
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").value("Not identified error"))
                .andExpect(jsonPath("$.path").value("/api/v1/unidades"));
    }
	
}
