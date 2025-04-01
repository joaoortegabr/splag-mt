package com.project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.project.entities.Lotacao;
import com.project.entities.ck.LotacaoCK;
import com.project.exceptions.ResourceNotFoundException;
import com.project.repositories.LotacaoRepository;
import com.project.service.CRUDService;
import com.project.utils.PaginationRequest;

import jakarta.validation.ConstraintViolationException;

@Service
public class LotacaoServiceImpl implements CRUDService<Lotacao> {
	
	private static final Logger log = LoggerFactory.getLogger(LotacaoServiceImpl.class);
	
	private final LotacaoRepository lotacaoRepository;
	
	public LotacaoServiceImpl(LotacaoRepository lotacaoRepository) {
		this.lotacaoRepository = lotacaoRepository;
	}
	
	public Page<Lotacao> findAll(PaginationRequest paginationRequest) {
		log.info("Executing service findAll");
	    PageRequest pageRequest = PageRequest.of(
	            paginationRequest.getPage(),
	            paginationRequest.getSize(),
	            Sort.by(Sort.Direction.fromString(paginationRequest.getSortDirection()), "lotId")
	        );
		Page<Lotacao> lotacaoPage = lotacaoRepository.findAll(pageRequest);
        return new PageImpl<>(lotacaoPage.getContent(), lotacaoPage.getPageable(), lotacaoPage.getTotalElements());
	}

	public Lotacao findById(Long lotId) {
		log.info("Executing service findById with params: lotId={}", lotId);
		
		LotacaoCK lotacaoId = new LotacaoCK();
		lotacaoId.setLotId(lotId);
		return lotacaoRepository.findById(lotacaoId)
			.orElseThrow(() -> new ResourceNotFoundException(lotId));
	}
	
	public Lotacao save(Lotacao lotacao) {
		log.info("Executing service save with param: {}", lotacao);
		try {
			return lotacaoRepository.save(lotacao);
		} catch(ConstraintViolationException e) {
			throw new ConstraintViolationException("Erro ao validar dados de entrada.", null);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Registro já existe no banco de dados.");
		}
	}
	
	public Lotacao update(Long id, Lotacao lotacao) {
		log.info("Executing service update with params: {} and {}", id, lotacao);
		Lotacao entity = findById(id);
		entity.setPesId(lotacao.getPesId());
		entity.setUnidId(lotacao.getUnidId());
		entity.setLotDataLotacao(lotacao.getLotDataLotacao());
		entity.setLotDataRemocao(lotacao.getLotDataRemocao());
		entity.setLotPortaria(lotacao.getLotPortaria());
		return lotacaoRepository.save(entity);
	}
	
	public String delete(Long lotId) {
		log.info("Executing service delete with param: lotId={}", lotId);
		LotacaoCK lotacaoId = new LotacaoCK();
		lotacaoId.setLotId(lotId);
		findById(lotId);
		lotacaoRepository.deleteById(lotacaoId);
		return "Registro removido com sucesso.";
	}

}
