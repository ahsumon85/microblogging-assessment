package com.ahasan.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.common.utils.StaticUrlProvider;
import com.ahasan.rest.dto.UserDTO;
import com.ahasan.rest.service.UserService;

@Validated
@RestController
@RequestMapping(StaticUrlProvider.BLOGGER_URL)
public class UserContoller {
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/sing-up")
	public ResponseEntity<BaseResponse> bloggerSignUp(@Valid @RequestBody UserDTO userDTO) {
		BaseResponse response = userService.bloggerSignUp(userDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
