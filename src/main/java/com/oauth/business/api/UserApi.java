package com.oauth.business.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

public interface UserApi<T,Object> {
	@PostMapping("/users")
	ResponseEntity<?> add(T t);

}