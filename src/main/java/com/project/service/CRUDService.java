package com.project.service;

import org.springframework.data.domain.Page;

import com.project.utils.PaginationRequest;

public interface CRUDService<T> {

	Page<T> findAll(PaginationRequest paginationRequest);
	
	T findById(Long id);
	
	T save(T objeto);
	
	T update(Long id, T objeto);
	
	String delete(Long id);
	
}
