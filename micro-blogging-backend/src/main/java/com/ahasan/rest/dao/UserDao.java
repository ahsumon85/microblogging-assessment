package com.ahasan.rest.dao;


import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.dto.UserDTO;

public abstract class UserDao {
	
	public abstract BaseResponse bloggerSignUp(UserDTO userDTO);

	public abstract UserDTO getUserInfoByUserName(String username);
}
