package com.oauth.business.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
@CrossOrigin
public interface RoleApi<T,Object> {
	@PostMapping("roles")
	ResponseEntity<?> add(T t);
	
}