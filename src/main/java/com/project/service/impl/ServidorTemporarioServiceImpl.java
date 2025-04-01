package com.project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.entities.ServidorTemporario;
import com.project.entities.ck.ServidorTemporarioCK;
import com.project.exceptions.ResourceNotFoundException;
import com.project.repositories.ServidorTemporarioRepository;
import com.project.service.CRUDService;
import com.project.utils.PaginationRequest;

import jakarta.validation.ConstraintViolationException;

@Service
public class ServidorTemporarioServiceImpl implements CRUDService<ServidorTemporario> {
	
	private static final Logger log = LoggerFactory.getLogger(ServidorTemporarioServiceImpl.class);
	
	private final ServidorTemporarioRepository servidorTemporarioRepository;
	
	public ServidorTemporarioServiceImpl(ServidorTemporarioRepository servidorTemporarioRepository) {
		this.servidorTemporarioRepository = servidorTemporarioRepository;
	}
	
	public Page<ServidorTemporario> findAll(PaginationRequest paginationRequest) {
		log.info("Executing service findAll");
	    PageRequest pageRequest = PageRequest.of(
	            paginationRequest.getPage(),
	            paginationRequest.getSize(),
	            Sort.by(Sort.Direction.fromString(paginationRequest.getSortDirection()), "pesId")
	        );
		Page<ServidorTemporario> servidorTemporarioPage = servidorTemporarioRepository.findAll(pageRequest);
        return new PageImpl<>(servidorTemporarioPage.getContent(), servidorTemporarioPage.getPageable(), servidorTemporarioPage.getTotalElements());
	}

	public ServidorTemporario findById(Long pesId) {
		log.info("Executing service findById with param: pesId={}", pesId);
		ServidorTemporarioCK servidorTemporarioId = new ServidorTemporarioCK();
		servidorTemporarioId.setPesId(pesId);
		return servidorTemporarioRepository.findById(servidorTemporarioId)
			.orElseThrow(() -> new ResourceNotFoundException(pesId));
	}
	
	public ServidorTemporario save(ServidorTemporario servidorTemporario) {
		log.info("Executing service save with param: {}", servidorTemporario);
		try {
			return servidorTemporarioRepository.save(servidorTemporario);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Erro ao validar dados de entrada.", null);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Registro já existe no banco de dados.");
		}
	}
	
	public ServidorTemporario update(Long pesId, ServidorTemporario servidorTemporario) {
		log.info("Executing service update with params: pesId={} and {}", pesId, servidorTemporario);
		ServidorTemporario servidorTemporarioAtualizado = findById(pesId);
		servidorTemporarioAtualizado.setPesId(servidorTemporario.getPesId());
		servidorTemporarioAtualizado.setStDataAdmissao(servidorTemporario.getStDataAdmissao());
		servidorTemporarioAtualizado.setStDataDemissao(servidorTemporario.getStDataDemissao());
		return servidorTemporarioRepository.save(servidorTemporarioAtualizado);
	}
	
	public String delete(Long pesId) {
		log.info("Executing service delete with param: pesId={}", pesId);
		ServidorTemporarioCK servidorTemporarioId = new ServidorTemporarioCK();
		servidorTemporarioId.setPesId(pesId);
		findById(pesId);
		servidorTemporarioRepository.deleteById(servidorTemporarioId);
		return "Registro removido com sucesso.";
	}

}
