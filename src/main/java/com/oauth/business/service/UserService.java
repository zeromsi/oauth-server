package com.oauth.business.service;

import java.util.List;
import com.oauth.business.UserDto;

public interface UserService<T, I> extends CoreService<T, I>{

	boolean checkUsersExistency(String username);
	boolean checkIfPasswordMeetsRequirements(String pass);
	boolean checkIfEmailAddressExistsAlready(String email);
}