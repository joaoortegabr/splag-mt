package com.project.service.impl;

import java.sql.Date;
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

import com.project.entities.ServidorEfetivo;
import com.project.entities.ck.ServidorEfetivoCK;
import com.project.exceptions.ResourceNotFoundException;
import com.project.models.dtos.ServidorEfetivoLotadoPorUnidadeResponse;
import com.project.repositories.ServidorEfetivoRepository;
import com.project.service.CRUDService;
import com.project.utils.CalculadoraIdade;
import com.project.utils.PaginationRequest;

import jakarta.validation.ConstraintViolationException;

@Service
public class ServidorEfetivoServiceImpl implements CRUDService<ServidorEfetivo> {
	
	private static final Logger log = LoggerFactory.getLogger(ServidorEfetivoServiceImpl.class);
	
	private final ServidorEfetivoRepository servidorEfetivoRepository;
	private final CalculadoraIdade calcularIdade;
	
	public ServidorEfetivoServiceImpl(ServidorEfetivoRepository servidorEfetivoRepository, CalculadoraIdade calcularIdade) {
		this.servidorEfetivoRepository = servidorEfetivoRepository;
		this.calcularIdade = calcularIdade;
	}
	
	public Page<ServidorEfetivo> findAll(PaginationRequest paginationRequest) {
		log.info("Executing service findAll");
	    PageRequest pageRequest = PageRequest.of(
	            paginationRequest.getPage(),
	            paginationRequest.getSize(),
	            Sort.by(Sort.Direction.fromString(paginationRequest.getSortDirection()), "pesId")
	        );
		Page<ServidorEfetivo> servidorEfetivoPage = servidorEfetivoRepository.findAll(pageRequest);
        return new PageImpl<>(servidorEfetivoPage.getContent(), servidorEfetivoPage.getPageable(), servidorEfetivoPage.getTotalElements());
	}

	public ServidorEfetivo findById(Long pesId) {
		log.info("Executing service findById with param: {}", pesId);
		ServidorEfetivoCK servidorEfetivoId = new ServidorEfetivoCK();
		servidorEfetivoId.setPesId(pesId);
		return servidorEfetivoRepository.findById(servidorEfetivoId)
			.orElseThrow(() -> new ResourceNotFoundException(pesId));
	}
	
	public ServidorEfetivo save(ServidorEfetivo servidorEfetivo) {
		log.info("Executing service save with param: {}", servidorEfetivo);
		try {
			return servidorEfetivoRepository.save(servidorEfetivo);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Erro ao validar dados de entrada.", null);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Registro já existe no banco de dados.");
		}
	}
	
	public ServidorEfetivo update(Long pesId, ServidorEfetivo servidorEfetivo) {
		log.info("Executing service update with params: pesId={} and {}", pesId, servidorEfetivo);
		ServidorEfetivo servidorEfetivoAtualizado = findById(pesId);
		servidorEfetivoAtualizado.setPesId(servidorEfetivo.getPesId());
		servidorEfetivoAtualizado.setSeMatricula(servidorEfetivo.getSeMatricula());
		return servidorEfetivoRepository.save(servidorEfetivoAtualizado);
	}
	
	public String delete(Long pesId) {
		log.info("Executing service delete with param: pesId={}", pesId);
		ServidorEfetivoCK servidorEfetivoId = new ServidorEfetivoCK();
		servidorEfetivoId.setPesId(pesId);
		findById(pesId);
		servidorEfetivoRepository.deleteById(servidorEfetivoId);
		return "Registro removido com sucesso.";
	}
	
	public Page<ServidorEfetivoLotadoPorUnidadeResponse> findServidorEfetivoLotadoPorUnidade(Long unidId, PaginationRequest paginationRequest) {
		log.info("Executing service findServidorEfetivoLotadoPorUnidade with param: unidId={}", unidId);
	    PageRequest pageRequest = PageRequest.of(
	            paginationRequest.getPage(),
	            paginationRequest.getSize(),
	            Sort.by(Sort.Direction.fromString(paginationRequest.getSortDirection()), "unidNome")
	        );
	    Page<Object[]> results = servidorEfetivoRepository.findServidorEfetivoLotadoPorUnidade(unidId, convertToPageable(pageRequest));
	    
	    List<ServidorEfetivoLotadoPorUnidadeResponse> responseList = results.stream()
	            .map(obj -> new ServidorEfetivoLotadoPorUnidadeResponse(
	                (String) obj[0],
	                calcularIdade.calcular((Date) obj[1]),
	                (String) obj[2],
	                (String) obj[3]
	            ))
	            .collect(Collectors.toList());
	    
	    return new PageImpl<>(responseList, pageRequest, results.getTotalElements());
	}
	
	private Pageable convertToPageable(PageRequest pageRequest) {
	    return PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize());
	}

}
