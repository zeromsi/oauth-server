package com.oauth.business.api;

import org.springframework.http.ResponseEntity;

public interface CoreApi<T,Object> {
	
	ResponseEntity<?> findAll();

	ResponseEntity<?> add(T t);
	
}