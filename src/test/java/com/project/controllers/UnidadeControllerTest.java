package com.project.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.entities.Unidade;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.mocks.UnidadeMock;
import com.project.service.impl.UnidadeServiceImpl;
import com.project.utils.PaginationRequest;

@WebMvcTest(UnidadeController.class)
class UnidadeControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private UnidadeServiceImpl unidadeService;

    @InjectMocks
    private UnidadeController unidadeController;
    
    @InjectMocks
    private UnidadeMock unidadeMock;

    private Unidade unidade;
    private List<Unidade> unidadeList;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        
        unidade = unidadeMock.single();
        unidadeList = unidadeMock.list();
    }

    @Test
    @DisplayName("Check if a list of Unidades is returned")
    void shouldReturnListOfunidades() throws Exception {
    	Page<Unidade> unidadePage = new PageImpl<>(unidadeList, PageRequest.of(0, unidadeList.size()), unidadeList.size());
    	
        when(unidadeService.findAll(any(PaginationRequest.class))).thenReturn(unidadePage);
        
        mockMvc.perform(get("/api/v1/unidades")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].unidId").value(1))
                .andExpect(jsonPath("$.content[0].unidNome").value("Cuiabá"));
        
        assertThat(unidadeList).hasSizeGreaterThan(0);
        assertThat(unidadeList.get(0).getUnidId()).isEqualTo(1);
        assertThat(unidadeList.get(0).getUnidNome()).isEqualTo("Cuiabá");
        
        verify(unidadeService, times(1)).findAll(any(PaginationRequest.class));
    }

    @Test
    @DisplayName("Check if an Unidade is returned")
    void shouldReturnAnunidade() throws Exception {
        when(unidadeService.findById(1L)).thenReturn(unidade);

        MvcResult mvcResult = mockMvc.perform(get("/api/v1/unidades/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.unidId").value(1))
                .andExpect(jsonPath("$.unidNome").value("Cuiabá"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Unidade returnedUnidade = objectMapper.readValue(content, Unidade.class);
        
        assertThat(returnedUnidade).isNotNull();
        assertThat(returnedUnidade.getUnidId()).isEqualTo(1);
        assertThat(returnedUnidade.getUnidNome()).isEqualTo("Cuiabá");
        
        verify(unidadeService, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Check if a new Unidade is created")
    void shouldCreateAnUnidade() throws Exception {
        when(unidadeService.save(any(Unidade.class))).thenReturn(unidade);

        MvcResult mvcResult = mockMvc.perform(post("/api/v1/unidades")
                .contentType(APPLICATION_JSON)
                .content("{\"id\":1,\"unidNome\":\"Cuiabá\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", containsString("/api/v1/unidades/1")))
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.unidId").value(1))
                .andExpect(jsonPath("$.unidNome").value("Cuiabá"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Unidade returnedUnidade = objectMapper.readValue(content, Unidade.class);

        assertThat(returnedUnidade).isNotNull();
        assertThat(returnedUnidade.getUnidId()).isEqualTo(1);
        assertThat(returnedUnidade.getUnidNome()).isEqualTo("Cuiabá");
        
        verify(unidadeService, times(1)).save(any(Unidade.class));
    }

    @Test
    @DisplayName("Check if the selected Unidade is updated")
    void shouldUpdateAnUnidade() throws Exception {
        when(unidadeService.update(eq(1L), any(Unidade.class))).thenReturn(unidade);

        MvcResult mvcResult = mockMvc.perform(put("/api/v1/unidades/1")
                .contentType(APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Cuiabá\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(APPLICATION_JSON))
                .andExpect(jsonPath("$.unidId").value(1))
                .andExpect(jsonPath("$.unidNome").value("Cuiabá"))
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Unidade updatedUnidade = objectMapper.readValue(content, Unidade.class);

        assertThat(updatedUnidade).isNotNull();
        assertThat(updatedUnidade.getUnidId()).isEqualTo(1);
        assertThat(updatedUnidade.getUnidNome()).isEqualTo("Cuiabá");
        
        verify(unidadeService, times(1)).update(eq(1L), any(Unidade.class));
    }

    @Test
    @DisplayName("Check if the selected Unidade is deleted")
    void shouldReturnUnidadeDeletedSuccessful() throws Exception {
    	when(unidadeService.delete(1L)).thenReturn("Registro removido com sucesso.");

    	MvcResult mvcResult = mockMvc.perform(delete("/api/v1/unidades/1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Registro removido com sucesso."))
                .andReturn();

    	String content = mvcResult.getResponse().getContentAsString();

        assertThat(content).isNotNull();
        assertThat(content).isEqualTo("Registro removido com sucesso.");
        assertThat(unidadeService.findById(1L)).isNull();
       
        verify(unidadeService, times(1)).delete(1L);
    }
    
    @Test
    @DisplayName("Check if method throws ResourceNotFoundException")
    void shouldThrowException() throws Exception {
        when(unidadeService.findById(4L)).thenThrow(new ResourceNotFoundException(4L));

    	Assertions.assertThrows(ResourceNotFoundException.class, () -> unidadeService.findById(4L));
    	
        verify(unidadeService, times(1)).findById(4L);
    }

}
