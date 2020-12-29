package com.ahasan.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

//	@PreAuthorize("hasRole('ROLE_BLOGGER') or hasRole('ROLE_ADMIN')")
//	@PostMapping("/comment/post")
//	public ResponseEntity<BaseResponse> commentOtherBloggerPost(@Valid @RequestBody CommentDTO commentDTO) {
//		BaseResponse response = bloggerService.commentOtherApprovedPost(commentDTO);
//		return new ResponseEntity<>(response, HttpStatus.CREATED);
//	}
//	
//	@PreAuthorize("hasRole('ROLE_BLOGGER')")
//	@DeleteMapping(value = "/delete/{userId}/{blogId}")
//	public ResponseEntity<BaseResponse> deleteOwnBlogPostById(@Valid @NotNull(message = "userId must not be null") @PathVariable("userId") int userId,
//															@Valid @NotNull(message = "blogId must not be null") @PathVariable("blogId") Long blogId) {
//		BaseResponse response = bloggerService.deleteOwnBlogPostById(userId, blogId);
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//	
//	@PreAuthorize("hasRole('ROLE_BLOGGER') or hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/find/post")
	public ResponseEntity<List<BlogDTO>> findAllBloggerPostByStatus() {
		List<BlogDTO> list = bloggerService.findAllBloggerPostByStatus(Status.ACTIVE.getCode(), Status.ACTIVE.getCode());
		return new ResponseEntity<List<BlogDTO>>(list, HttpStatus.OK);
	}
	
}
