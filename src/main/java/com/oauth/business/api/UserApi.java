package com.oauth.business.api;

import org.springframework.http.ResponseEntity;

public interface UserApi<T,Object> extends CoreApi<T, Object>{

	ResponseEntity<?> findAllRolesByUserId(Integer id);

}