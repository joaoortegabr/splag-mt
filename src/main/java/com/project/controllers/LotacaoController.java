package com.project.controllers;

import java.net.URI;

import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.entities.Lotacao;
import com.project.models.dtos.LotacaoRequest;
import com.project.models.dtos.LotacaoResponse;
import com.project.models.mappers.LotacaoMapper;
import com.project.service.impl.LotacaoServiceImpl;
import com.project.utils.PaginationRequest;

@RestController
@RequestMapping(value = "/v1/lotacoes")
public class LotacaoController {
	
	private static final Logger log = LoggerFactory.getLogger(LotacaoController.class);
	
	private final LotacaoServiceImpl lotacaoService;
	
	LotacaoMapper mapper = Mappers.getMapper(LotacaoMapper.class);
	
	public LotacaoController(LotacaoServiceImpl lotacaoService) {
		this.lotacaoService = lotacaoService;
	}
	
	@GetMapping
	public ResponseEntity<Page<LotacaoResponse>> findAll(PaginationRequest paginationRequest) {
		log.info("Receiving request to findAll");
		Page<Lotacao> lotacaoPage = lotacaoService.findAll(paginationRequest);
		Page<LotacaoResponse> lotacaoResponsePage = lotacaoPage.map(mapper::toLotacaoResponse);

	    return ResponseEntity.ok(lotacaoResponsePage);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<LotacaoResponse>findById(@PathVariable Long lotId) {
		log.info("Receiving request to findById with params: lotId={}", lotId);
		LotacaoResponse lotacao = mapper.toLotacaoResponse(lotacaoService.findById(lotId));
		return ResponseEntity.ok().body(lotacao);
	}
	
	@PostMapping
	public ResponseEntity<LotacaoResponse> save(@RequestBody LotacaoRequest lotacaoRequest) {
		log.info("Receiving request to save with param: {}", lotacaoRequest);
		Lotacao lotacao = mapper.toLotacao(lotacaoRequest);
		lotacaoService.save(lotacao);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(lotacao.getUnidId()).toUri();
		LotacaoResponse savedLotacaoResponse = mapper.toLotacaoResponse(lotacao);
		return ResponseEntity.created(uri).body(savedLotacaoResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<LotacaoResponse> update(@PathVariable Long id, @RequestBody LotacaoRequest lotacaoRequest) {
		log.info("Receiving request to update with params: {} and {}", id, lotacaoRequest);
		Lotacao lotacao = mapper.toLotacao(lotacaoRequest);
		LotacaoResponse updatedLotacaoResponse = mapper.toLotacaoResponse(lotacaoService.update(id, lotacao));
		return ResponseEntity.ok().body(updatedLotacaoResponse);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("Receiving request to delete with param: {}", id);
		String msg = lotacaoService.delete(id);
		return ResponseEntity.ok(msg);
	}

}
