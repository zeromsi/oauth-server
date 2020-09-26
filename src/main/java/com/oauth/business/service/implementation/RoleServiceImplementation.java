package com.oauth.business.service.implementation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import com.oauth.business.RoleDto;
import com.oauth.business.common.Converter;
import com.oauth.business.service.RoleService;
import com.oauth.entity.Role;
import com.oauth.entity.User;
import com.oauth.entity.repository.RoleRepository;
import com.oauth.entity.repository.UserRepository;


@Service
public class RoleServiceImplementation implements RoleService<RoleDto, Integer> {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	private Environment environment;

	@Override
	public Boolean save(RoleDto roleVM) {
		try {
			Role role=roleRepository.save(Converter.getRole(roleVM));
			return true;
		} catch (Exception e) {
			return false;
		}

	}
	
	@Override
	public Boolean saveAll(List<RoleDto> roleVMList) {
		try {
			roleRepository.saveAll(Converter.getRoleList(roleVMList));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public RoleDto findById(Integer id) {
		return Converter.getRoleDto(roleRepository.getOne(id));
	}

	@Override
	public List<RoleDto> findAll() {
		return Converter.getRoleDtoList(roleRepository.findAll());
	}
	
	@Override
	public List<RoleDto> findAllByUserId(Integer userId) {
		User user=userRepository.findById(userId).orElse(null);
		return Converter.getRoleDtoList(new ArrayList<>(user.getRoles()));
		
	}

}