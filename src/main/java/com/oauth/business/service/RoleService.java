package com.oauth.business.service;
import java.util.List;

import com.oauth.business.RoleDto;

public interface RoleService<T,I> extends CoreService<T,I>{
	List<RoleDto> findAllByUserId(Integer userId);
}