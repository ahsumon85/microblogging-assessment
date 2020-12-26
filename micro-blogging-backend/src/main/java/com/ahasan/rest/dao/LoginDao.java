package com.ahasan.rest.dao;


import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.dto.UserDTO;

public abstract class LoginDao {
	public abstract BaseResponse bloggerSignUp(UserDTO userDTO);
}
