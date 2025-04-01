package com.project.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.entities.Unidade;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.mocks.UnidadeMock;
import com.project.repositories.UnidadeRepository;
import com.project.service.impl.UnidadeServiceImpl;
import com.project.utils.PaginationRequest;

import jakarta.persistence.EntityNotFoundException;

class UnidadeServiceImplTest {

    @Mock
    private UnidadeRepository unidadeRepository;
    
    @InjectMocks
    private UnidadeServiceImpl unidadeService;
    
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
    void shouldReturnListOfActiveUnidades() {
        PaginationRequest paginationRequest = new PaginationRequest(0,unidadeList.size(), "unidNome", "asc");
    	Page<Unidade> unidadePage = new PageImpl<>(unidadeList, PageRequest.of(0, unidadeList.size()), unidadeList.size());

        when(unidadeRepository.findAll(any(PageRequest.class))).thenReturn(unidadePage);
        Page<Unidade> result = unidadeService.findAll(paginationRequest);

        assertEquals(unidadeList.size(), result.getContent().size());
        assertEquals(unidadeList.get(0), result.getContent().get(0));
        assertEquals(unidadeList.get(1), result.getContent().get(1));
        verify(unidadeRepository, times(1)).findAll(any(PageRequest.class));
    }
 
    @Test
    @DisplayName("Check if a single Unidade is returned successfully")
    void shouldReturnOneUnidadeSuccessfully() {
        when(unidadeRepository.findById(1L)).thenReturn(Optional.of(unidade));

        Unidade result = unidadeService.findById(1L);

        assertEquals(unidade, result);
        verify(unidadeRepository, times(1)).findById(1L);
    }
    
    @Test
    @DisplayName("Check if Exception is thrown when Unidade is not found")
    void shouldThrowExceptionWhenUnidadeNotFound() {
        when(unidadeRepository.findById(4L)).thenReturn(Optional.of(unidade));

        assertThrows(ResourceNotFoundException.class, () -> unidadeService.findById(4L));
        verify(unidadeRepository, times(1)).findById(4L);
    }

    @Test
    @DisplayName("Check if a new Unidade is created")
    void shouldCreateNewUnidade() {
        when(unidadeRepository.save(any(Unidade.class))).thenReturn(unidade);
        
        Unidade result = unidadeService.save(unidade);

        assertEquals(unidade, result);
        verify(unidadeRepository, times(1)).save(any(Unidade.class));
    }
    
    @Test
    @DisplayName("Check if DataIntegrityViolationExceptio is correctly thrown")
    void shouldTestThrowsDataIntegrityViolationException() {
    	when(unidadeRepository.save(any(Unidade.class))).thenReturn(unidade);
        unidadeService.save(unidade);
        
        doThrow(new DataIntegrityViolationException("Registro já existe no banco de dados.")).when(unidadeRepository).save(unidade);

        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class, () -> {
            unidadeService.save(unidade);
        });

        assertEquals("Registro já existe no banco de dados.", exception.getMessage());
        verify(unidadeRepository, times(1)).save(any(Unidade.class));
    }

    @Test
    @DisplayName("Check if the selected Unidade is updated")
    void shouldUpdateUnidade() {
        when(unidadeRepository.findById(1L)).thenReturn(Optional.of(unidade));
        when(unidadeRepository.save(any(Unidade.class))).thenAnswer(invocation -> {
        	Unidade savedUnidade = invocation.getArgument(0);
            return savedUnidade;
        });

        Unidade updatedUnidade = new Unidade();
        updatedUnidade.setUnidNome("Novo Nome");
        Unidade result = unidadeService.update(1L, updatedUnidade);

        assertEquals("Novo Nome", result.getUnidNome());
        verify(unidadeRepository, times(1)).findById(1L);
        verify(unidadeRepository, times(1)).save(any(Unidade.class));
    }
    
    @Test
    @DisplayName("Check if trying to update a non-existing Unidade throws Exception")
    void shouldNotUpdateNonExistingUnidade() {
        when(unidadeRepository.findById(1L)).thenThrow(new EntityNotFoundException());

        assertThrows(ResourceNotFoundException.class, () -> unidadeService.update(1L, unidade));

        verify(unidadeRepository, times(1)).findById(1L);
        verify(unidadeRepository, never()).save(any(Unidade.class));
    }
    
    @Test
    @DisplayName("Check if a Unidade is deleted")
    void shouldDeleteUnidade() {
    	doNothing().when(unidadeRepository).deleteById(1L);

        String result = unidadeService.delete(1L);

        assertEquals(result, "Registro removido com sucesso");
        verify(unidadeRepository, times(1)).deleteById(1L);
    }
    
}

