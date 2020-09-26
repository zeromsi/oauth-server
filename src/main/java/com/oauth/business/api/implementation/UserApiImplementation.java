package com.oauth.business.api.implementation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oauth.business.UserDto;
import com.oauth.business.api.UserApi;
import com.oauth.business.service.RoleService;
import com.oauth.business.service.UserService;


@CrossOrigin
@RestController
@RequestMapping("api/v1/")
public class UserApiImplementation implements UserApi<UserDto, Integer> {
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	

	@Override
	public ResponseEntity<?> add(@RequestBody UserDto dto) {

		boolean checkIfUserExistsAlready = userService.checkUsersExistency(dto.getUsername());
		boolean checkIfEmailAddressExistsAlready = userService.checkIfEmailAddressExistsAlready(dto.getEmail());
		if (checkIfUserExistsAlready == true) {
			return new ResponseEntity<String>("Username already exists",HttpStatus.BAD_REQUEST);
		} else if (checkIfEmailAddressExistsAlready == true) {
			return new ResponseEntity<String>("Email already exists",HttpStatus.BAD_REQUEST);
		} else {
			boolean success = userService.save(dto);
			if (success) {
				return new ResponseEntity<UserDto>(dto, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}

	


}