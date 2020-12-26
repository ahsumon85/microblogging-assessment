package com.ahasan.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.dao.UserDao;
import com.ahasan.rest.dto.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public BaseResponse bloggerSignUp(UserDTO userDTO) {
		return userDao.bloggerSignUp(userDTO);
	}

}
