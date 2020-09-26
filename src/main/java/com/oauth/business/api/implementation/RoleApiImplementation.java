package com.oauth.business.api.implementation;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oauth.business.RoleDto;
import com.oauth.business.api.RoleApi;
import com.oauth.business.service.RoleService;

@CrossOrigin
@RestController
@RequestMapping("api/v1/")
public class RoleApiImplementation implements RoleApi<RoleDto, Integer> {

	@Autowired
	RoleService roleService;

	@Override
	@PreAuthorize("hasAuthority('role_super_admin')")
	public ResponseEntity<?> add(@RequestBody RoleDto roleDto) {;
		boolean success = roleService.save(roleDto);
		if (success) {
			return new ResponseEntity<RoleDto>(roleDto, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}




}