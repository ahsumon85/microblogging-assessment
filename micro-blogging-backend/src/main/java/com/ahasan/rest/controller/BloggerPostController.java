package com.ahasan.rest.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.common.utils.StaticUrlProvider;
import com.ahasan.rest.common.utils.Status;
import com.ahasan.rest.dto.BlogDTO;
import com.ahasan.rest.dto.UpAndDownvoteDTO;
import com.ahasan.rest.service.BloggerService;

@RestController
@RequestMapping(StaticUrlProvider.BLOGGER_URL)
@Validated
public class BloggerPostController {

	@Autowired
	private BloggerService bloggerService;

	@PostMapping("/post/create")
	public ResponseEntity<BaseResponse> createBlogPostByBlogger(@Valid @RequestBody BlogDTO blogDTO) {
		BaseResponse response = bloggerService.createBlogPostByBlogger(blogDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/vote/post")
	public ResponseEntity<BaseResponse> upAndDownvote(@Valid @RequestBody UpAndDownvoteDTO upAndDownvoteDTO) {
		BaseResponse response = bloggerService.upAndDownvote(upAndDownvoteDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PostMapping("/approved/vote")
	public ResponseEntity<BaseResponse> approvedUpAndDownvote(@Valid @RequestBody UpAndDownvoteDTO upAndDownvoteDTO) {
		BaseResponse response = bloggerService.approvedUpAndDownvote(upAndDownvoteDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping(value = "/find/post")
	public ResponseEntity<List<BlogDTO>> findAllBloggerPostByStatus(@RequestParam Integer status) {
		List<BlogDTO> list = bloggerService.findAllBloggerPostByStatus(Status.ACTIVE.getCode(), status);
		return new ResponseEntity<List<BlogDTO>>(list, HttpStatus.OK);
	}

	@GetMapping(value = "/find/post/by-name")
	public ResponseEntity<List<BlogDTO>> findAllBLoggerPostUserName(@RequestParam String username) {
		List<BlogDTO> blogDTOs = bloggerService.findAllBLoggerPostUserName(username);
		return new ResponseEntity<List<BlogDTO>>(blogDTOs, HttpStatus.OK);
	}

}
