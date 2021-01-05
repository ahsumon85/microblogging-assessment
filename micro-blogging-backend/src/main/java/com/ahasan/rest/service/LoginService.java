package com.ahasan.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.dao.LoginDao;
import com.ahasan.rest.dto.UserDTO;

@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;

	public BaseResponse userSignUp(UserDTO userDTO) {
		return loginDao.userSignUp(userDTO);
	}

}
