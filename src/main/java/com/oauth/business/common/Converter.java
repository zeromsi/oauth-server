package com.oauth.business.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import com.oauth.business.RoleDto;
import com.oauth.business.UserDto;
import com.oauth.entity.Permission;
import com.oauth.entity.Role;
import com.oauth.entity.User;

public class Converter {
	
	private static final AtomicLong TS = new AtomicLong(System.currentTimeMillis() * 1000);
	
	public static long getUniqueTimestamp() {
	    return TS.incrementAndGet();
	}

	public static Role getRole(RoleDto roleVM) {
		Role role = new Role();
		try {
		role.setName(roleVM.getName());
		}catch(Exception e) {
			
		}
		return role;
	}
	public static List<Role> getRoleList(List<RoleDto> roleVMList) {
		List<Role> roleList = new ArrayList<>();
		roleVMList.stream().forEach(roleVM -> roleList.add(getRole(roleVM)));
		return roleList;
	}

	public static RoleDto getRoleDto(Role role) {
		if (role != null) {
			RoleDto roleVM = new RoleDto();
			roleVM.setId(role.getId());
			roleVM.setName(role.getName());
			return roleVM;
		}
		return null;
	}

	public static List<RoleDto> getRoleDtoList(List<Role> roleList) {
		List<RoleDto> roleVMList = new ArrayList<>();
		roleList.forEach(i -> roleVMList.add(getRoleDto(i)));
		return roleVMList;
	}

	public static User getUser(UserDto userVM) {
		User user = new User();
		user.setId(userVM.getId());
		user.setEmail(userVM.getEmail());
		user.setAccountNonExpired(userVM.isAccountNonExpired());
		user.setAccountNonLocked(userVM.isAccountNonLocked());
		user.setCredentialsNonExpired(userVM.isCredentialsNonExpired());
		user.setEnabled(userVM.isEnabled());
		user.setPassword(userVM.getPassword());
		user.setName(userVM.getName());
		user.setUsername(userVM.getUsername());
		return user;
	}


	public static List<User> getUserList(List<UserDto> userVMList) {
		List<User> userList = new ArrayList<>();
		userVMList.forEach(i -> userList.add(getUser(i)));
		return userList;
	}

	public static List<UserDto> getUserDtoList(List<User> userList) {
		List<UserDto> userVMList = new ArrayList<>();
		userList.forEach(i -> {
			userVMList.add(getUserVM(i));
		});
		return userVMList;
	}

	public static UserDto getUserVM(User user) {
		UserDto userVM = new UserDto();
		userVM.setId(user.getId());
		userVM.setEmail(user.getEmail());
		userVM.setAccountNonExpired(user.isAccountNonExpired());
		userVM.setAccountNonLocked(user.isAccountNonLocked());
		userVM.setCredentialsNonExpired(user.isCredentialsNonExpired());
		userVM.setUsername(user.getUsername());
		userVM.setEnabled(user.isEnabled());
		userVM.setName(user.getName());
		return userVM;
	}



}