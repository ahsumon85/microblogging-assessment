package com.ahasan.rest.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.dao.BloggerDao;
import com.ahasan.rest.dto.BlogDTO;
import com.ahasan.rest.dto.UpAndDownvoteDTO;

@Service
public class BloggerService {
	
	@Autowired
	private BloggerDao bloggerDao;

	public BaseResponse  createBlogPostByBlogger(BlogDTO blogDTO) {
		return bloggerDao.createBlogPostByBlogger(blogDTO);
	}

	public List<BlogDTO> findAllBloggerPostByStatus(int publish, Integer status){
		return bloggerDao.findAllBloggerPostByStatus(publish, status);
	}

	public BaseResponse upAndDownvote(UpAndDownvoteDTO upAndDownvoteDTO) {
		return bloggerDao.upAndDownvote(upAndDownvoteDTO);
	}
	
	public BaseResponse deleteOwnBlogPostById(Long blogId) {
		return bloggerDao.deleteOwnBlogPostById(blogId);
	}
	
	public List<BlogDTO> findAllBLoggerPostUserName(String username) {
		return bloggerDao.findAllBLoggerPostUserName(username);
	}

	public BaseResponse approvedUpAndDownvote(UpAndDownvoteDTO upAndDownvoteDTO) {
		return bloggerDao.approvedUpAndDownvote(upAndDownvoteDTO);
	}

//	public BaseResponse commentOtherApprovedPost(CommentDTO commentDTO);
}
