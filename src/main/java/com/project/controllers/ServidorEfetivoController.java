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

import com.project.entities.ServidorEfetivo;
import com.project.models.dtos.ServidorEfetivoLotadoPorUnidadeResponse;
import com.project.models.dtos.ServidorEfetivoRequest;
import com.project.models.dtos.ServidorEfetivoResponse;
import com.project.models.mappers.ServidorEfetivoMapper;
import com.project.service.impl.ServidorEfetivoServiceImpl;
import com.project.utils.PaginationRequest;

@RestController
@RequestMapping(value = "/v1/servidores-efetivos")
public class ServidorEfetivoController {
	
	private static final Logger log = LoggerFactory.getLogger(ServidorEfetivoController.class);
	
	private final ServidorEfetivoServiceImpl servidorEfetivoService;
	
	ServidorEfetivoMapper mapper = Mappers.getMapper(ServidorEfetivoMapper.class);
	
	public ServidorEfetivoController(ServidorEfetivoServiceImpl servidorEfetivoService) {
		this.servidorEfetivoService = servidorEfetivoService;
	}
	
	@GetMapping
	public ResponseEntity<Page<ServidorEfetivoResponse>> findAll(PaginationRequest paginationRequest) {
		log.info("Receiving request to findAll");
		Page<ServidorEfetivo> servidorEfetivoPage = servidorEfetivoService.findAll(paginationRequest);
		Page<ServidorEfetivoResponse> servidorEfetivoResponsePage = servidorEfetivoPage.map(mapper::toServidorEfetivoResponse);

	    return ResponseEntity.ok(servidorEfetivoResponsePage);
	}
	
	@GetMapping(value = "/{pesId}")
	public ResponseEntity<ServidorEfetivoResponse>findById(@PathVariable Long pesId) {
		log.info("Receiving request to findById with param: pesId={}", pesId);
		ServidorEfetivoResponse servidorEfetivo = mapper.toServidorEfetivoResponse(servidorEfetivoService.findById(pesId));
		return ResponseEntity.ok().body(servidorEfetivo);
	}
	
	@PostMapping
	public ResponseEntity<ServidorEfetivoResponse> save(@RequestBody ServidorEfetivoRequest servidorEfetivoRequest) {
		log.info("Receiving request to save with param: {}", servidorEfetivoRequest);
		ServidorEfetivo servidorEfetivo = mapper.toServidorEfetivo(servidorEfetivoRequest);
		servidorEfetivoService.save(servidorEfetivo);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(servidorEfetivo.getPesId()).toUri();
		ServidorEfetivoResponse savedServidorEfetivoResponse = mapper.toServidorEfetivoResponse(servidorEfetivo);
		return ResponseEntity.created(uri).body(savedServidorEfetivoResponse);
	}
	
	@PutMapping(value = "/{pesId}")
	public ResponseEntity<ServidorEfetivoResponse> update(@PathVariable Long pesId, @RequestBody ServidorEfetivoRequest servidorEfetivoRequest) {
		log.info("Receiving request to update with params: pesId={} and {}", pesId, servidorEfetivoRequest);
		ServidorEfetivo servidorEfetivo = mapper.toServidorEfetivo(servidorEfetivoRequest);
		ServidorEfetivoResponse updatedServidorEfetivoResponse = mapper.toServidorEfetivoResponse(servidorEfetivoService.update(pesId, servidorEfetivo));
		return ResponseEntity.ok().body(updatedServidorEfetivoResponse);
	}
	
	@DeleteMapping(value = "/{pesId}")
	public ResponseEntity<String> delete(@PathVariable Long pesId) {
		log.info("Receiving request to delete with param: pesId={}", pesId);
		String msg = servidorEfetivoService.delete(pesId);
		return ResponseEntity.ok(msg);
	}

	@GetMapping(value = "/lotados/{unidId}")
	public ResponseEntity<Page<ServidorEfetivoLotadoPorUnidadeResponse>> findServidorEfetivoLotadoPorUnidade(@PathVariable Long unidId, PaginationRequest paginationRequest) {
		log.info("Receiving request to findServidorEfetivoLotadoPorUnidade with param: unidId={}", unidId);
		Page<ServidorEfetivoLotadoPorUnidadeResponse> resultPage = servidorEfetivoService.findServidorEfetivoLotadoPorUnidade(unidId, paginationRequest);
	    return ResponseEntity.ok(resultPage);
	}
	
}
