package com.ahasan.rest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.dto.UserDTO;
import com.ahasan.rest.service.LoginService;

@CrossOrigin(origins = "http://localhost:8085")
@Validated
@RestController
@RequestMapping("/user")
public class LoginContoller {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping(value = { "/sing-up"})
	public ResponseEntity<BaseResponse> bloggerSignUp(@Valid @RequestBody UserDTO userDTO) {
		BaseResponse response = loginService.bloggerSignUp(userDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
