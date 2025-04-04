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

import com.project.entities.Unidade;
import com.project.models.dtos.EnderecoUnidadePorNomeServidorResponse;
import com.project.models.dtos.UnidadeRequest;
import com.project.models.dtos.UnidadeResponse;
import com.project.models.mappers.UnidadeMapper;
import com.project.service.impl.UnidadeServiceImpl;
import com.project.utils.PaginationRequest;

@RestController
@RequestMapping(value = "/v1/unidades")
public class UnidadeController {
	
	private static final Logger log = LoggerFactory.getLogger(UnidadeController.class);
	
	private final UnidadeServiceImpl unidadeService;
	
	UnidadeMapper mapper = Mappers.getMapper(UnidadeMapper.class);
	
	public UnidadeController(UnidadeServiceImpl unidadeService) {
		this.unidadeService = unidadeService;
	}
	
	@GetMapping
	public ResponseEntity<Page<UnidadeResponse>> findAll(PaginationRequest paginationRequest) {
		log.info("Receiving request to findAll");
		Page<Unidade> unidadePage = unidadeService.findAll(paginationRequest);
		Page<UnidadeResponse> unidadeResponsePage = unidadePage.map(mapper::toUnidadeResponse);

	    return ResponseEntity.ok(unidadeResponsePage);
	}
	
	@GetMapping(value = "/{unidId}")
	public ResponseEntity<UnidadeResponse>findById(@PathVariable Long unidId) {
		log.info("Receiving request to findById with param: unidId={}", unidId);
		UnidadeResponse unidade = mapper.toUnidadeResponse(unidadeService.findById(unidId));
		return ResponseEntity.ok().body(unidade);
	}
	
	@PostMapping
	public ResponseEntity<UnidadeResponse> save(@RequestBody UnidadeRequest unidadeRequest) {
		log.info("Receiving request to save with param: {}", unidadeRequest);
		Unidade unidade = mapper.toUnidade(unidadeRequest);
		unidadeService.save(unidade);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(unidade.getUnidId()).toUri();
		UnidadeResponse savedUnidadeResponse = mapper.toUnidadeResponse(unidade);
		return ResponseEntity.created(uri).body(savedUnidadeResponse);
	}
	
	@PutMapping(value = "/{unidId}")
	public ResponseEntity<UnidadeResponse> update(@PathVariable Long unidId, @RequestBody UnidadeRequest unidadeRequest) {
		log.info("Receiving request to update with params: unidId={} and {}", unidId, unidadeRequest);
		Unidade unidade = mapper.toUnidade(unidadeRequest);
		UnidadeResponse updatedUnidadeResponse = mapper.toUnidadeResponse(unidadeService.update(unidId, unidade));
		return ResponseEntity.ok().body(updatedUnidadeResponse);
	}
	
	@DeleteMapping(value = "/{unidId}")
	public ResponseEntity<String> delete(@PathVariable Long unidId) {
		log.info("Receiving request to delete with param: unidId={}", unidId);
		String msg = unidadeService.delete(unidId);
		return ResponseEntity.ok(msg);
	}

	@GetMapping(value = "/busca/{pesNome}")
	public ResponseEntity<Page<EnderecoUnidadePorNomeServidorResponse>> findEnderecoUnidadePorNomeServidor(@PathVariable String pesNome, PaginationRequest paginationRequest) {
		log.info("Receiving request to findEnderecoUnidadePorNomeServidor with param: pesNome={}", pesNome);
		Page<EnderecoUnidadePorNomeServidorResponse> resultPage = unidadeService.findEnderecoUnidadePorNomeServidor(pesNome, paginationRequest);
	    return ResponseEntity.ok(resultPage);
	}
	
}
