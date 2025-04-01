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

import com.project.entities.ServidorTemporario;
import com.project.models.dtos.ServidorTemporarioRequest;
import com.project.models.dtos.ServidorTemporarioResponse;
import com.project.models.mappers.ServidorTemporarioMapper;
import com.project.service.impl.ServidorTemporarioServiceImpl;
import com.project.utils.PaginationRequest;

@RestController
@RequestMapping(value = "/v1/servidores-temporarios")
public class ServidorTemporarioController {
	
	private static final Logger log = LoggerFactory.getLogger(ServidorTemporarioController.class);
	
	private final ServidorTemporarioServiceImpl servidorTemporarioService;
	
	ServidorTemporarioMapper mapper = Mappers.getMapper(ServidorTemporarioMapper.class);
	
	public ServidorTemporarioController(ServidorTemporarioServiceImpl servidorTemporarioService) {
		this.servidorTemporarioService = servidorTemporarioService;
	}
	
	@GetMapping
	public ResponseEntity<Page<ServidorTemporarioResponse>> findAll(PaginationRequest paginationRequest) {
		log.info("Receiving request to findAll");
		Page<ServidorTemporario> servidorTemporarioPage = servidorTemporarioService.findAll(paginationRequest);
		Page<ServidorTemporarioResponse> servidorTemporarioResponsePage = servidorTemporarioPage.map(mapper::toServidorTemporarioResponse);

	    return ResponseEntity.ok(servidorTemporarioResponsePage);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ServidorTemporarioResponse>findById(@PathVariable Long id) {
		log.info("Receiving request to findById with param: {}", id);
		ServidorTemporarioResponse servidorTemporario = mapper.toServidorTemporarioResponse(servidorTemporarioService.findById(id));
		return ResponseEntity.ok().body(servidorTemporario);
	}
	
	@PostMapping
	public ResponseEntity<ServidorTemporarioResponse> save(@RequestBody ServidorTemporarioRequest servidorTemporarioRequest) {
		log.info("Receiving request to save with param: {}", servidorTemporarioRequest);
		ServidorTemporario servidorTemporario = mapper.toServidorTemporario(servidorTemporarioRequest);
		servidorTemporarioService.save(servidorTemporario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(servidorTemporario.getPesId()).toUri();
		ServidorTemporarioResponse savedServidorTemporarioResponse = mapper.toServidorTemporarioResponse(servidorTemporario);
		return ResponseEntity.created(uri).body(savedServidorTemporarioResponse);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ServidorTemporarioResponse> update(@PathVariable Long id, @RequestBody ServidorTemporarioRequest servidorTemporarioRequest) {
		log.info("Receiving request to update with params: {} and {}", id, servidorTemporarioRequest);
		ServidorTemporario servidorTemporario = mapper.toServidorTemporario(servidorTemporarioRequest);
		ServidorTemporarioResponse updatedServidorTemporarioResponse = mapper.toServidorTemporarioResponse(servidorTemporarioService.update(id, servidorTemporario));
		return ResponseEntity.ok().body(updatedServidorTemporarioResponse);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		log.info("Receiving request to delete with param: {}", id);
		String msg = servidorTemporarioService.delete(id);
		return ResponseEntity.ok(msg);
	}

}
