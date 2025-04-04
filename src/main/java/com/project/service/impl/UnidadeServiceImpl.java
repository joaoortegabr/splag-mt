package com.project.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.entities.Unidade;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.dtos.EnderecoUnidadePorNomeServidorResponse;
import com.project.repositories.UnidadeRepository;
import com.project.service.CRUDService;
import com.project.utils.PaginationRequest;

import jakarta.validation.ConstraintViolationException;

@Service
public class UnidadeServiceImpl implements CRUDService<Unidade> {
	
	private static final Logger log = LoggerFactory.getLogger(UnidadeServiceImpl.class);
	
	private final UnidadeRepository unidadeRepository;
	
	public UnidadeServiceImpl(UnidadeRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}
	
	public Page<Unidade> findAll(PaginationRequest paginationRequest) {
		log.info("Executing service findAll");
	    PageRequest pageRequest = PageRequest.of(
	            paginationRequest.getPage(),
	            paginationRequest.getSize(),
	            Sort.by(Sort.Direction.fromString(paginationRequest.getSortDirection()), "unidNome")
	        );
		Page<Unidade> unidadePage = unidadeRepository.findAll(pageRequest);
        return new PageImpl<>(unidadePage.getContent(), unidadePage.getPageable(), unidadePage.getTotalElements());
	}

	public Unidade findById(Long unidId) {
		log.info("Executing service findById with param: unidId={}", unidId);
		return unidadeRepository.findById(unidId)
			.orElseThrow(() -> new ResourceNotFoundException(unidId));
	}
	
	public Unidade save(Unidade unidade) {
		log.info("Executing service save with param: {}", unidade);
		try {
			return unidadeRepository.save(unidade);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Erro ao validar dados de entrada.", null);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Registro já existe no banco de dados.");
		}
	}
	
	public Unidade update(Long unidId, Unidade unidade) {
		log.info("Executing service update with params: unidId={} and {}", unidId, unidade);
		Unidade entity = findById(unidId);
		entity.setUnidNome(unidade.getUnidNome());
		entity.setUnidSigla(unidade.getUnidSigla());
		return unidadeRepository.save(entity);
	}
	
	public String delete(Long unidId) {
		log.info("Executing service delete with param: unidId={}", unidId);
		findById(unidId);
		unidadeRepository.deleteById(unidId);
		return "Registro removido com sucesso.";
	}
	
	public Page<EnderecoUnidadePorNomeServidorResponse> findEnderecoUnidadePorNomeServidor(String pesNome, PaginationRequest paginationRequest) {
		log.info("Executing service findEnderecoUnidadePorNomeServidor with param: pesNome={}", pesNome);
	    PageRequest pageRequest = PageRequest.of(
	            paginationRequest.getPage(),
	            paginationRequest.getSize(),
	            Sort.by(Sort.Direction.fromString(paginationRequest.getSortDirection()), "cidNome")
	        );
	    Page<Object[]> results = unidadeRepository.findnderecoPorNomeServidor(pesNome, convertToPageable(pageRequest));
	    
	    List<EnderecoUnidadePorNomeServidorResponse> responseList = results.stream()
	            .map(obj -> new EnderecoUnidadePorNomeServidorResponse(
	                (String) obj[0],
	                (String) obj[1],
	                (Integer) obj[2],
	                (String) obj[3],
	                (String) obj[4],
	                (String) obj[5]
	            ))
	            .collect(Collectors.toList());
	    
	    return new PageImpl<>(responseList, pageRequest, results.getTotalElements());
	}
	
	private Pageable convertToPageable(PageRequest pageRequest) {
	    return PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
	}
	
}
