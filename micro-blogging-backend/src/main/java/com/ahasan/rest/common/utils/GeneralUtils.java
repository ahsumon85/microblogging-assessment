package com.ahasan.rest.common.utils;

import org.springframework.beans.BeanUtils;

import com.ahasan.rest.dto.RoleDTO;
import com.ahasan.rest.dto.UserDTO;
import com.ahasan.rest.entity.User;

public class GeneralUtils {
	
	
	public static UserDTO provideUserToUserDto(User user) {
		UserDTO userDTO = new UserDTO();
		RoleDTO roleDTO = new RoleDTO();
		BeanUtils.copyProperties(user, userDTO);
		BeanUtils.copyProperties(user.getRoles().stream().findFirst().orElse(null), roleDTO);
		userDTO.setRole(roleDTO);
		return userDTO;
	}

}
