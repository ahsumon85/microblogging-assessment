package com.ahasan.rest.service;

import java.util.Collections;
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

	public BaseResponse createBlogPostByBlogger(BlogDTO blogDTO) {
		return bloggerDao.createBlogPostByBlogger(blogDTO);
	}

	public List<BlogDTO> findAllBloggerPostByStatus(int publish, Integer status) {
		List<BlogDTO> blogDTOs = bloggerDao.findAllBloggerPostByStatus(publish, status);
		Collections.reverse(blogDTOs);
		return blogDTOs;
	}

	public BaseResponse upAndDownvote(UpAndDownvoteDTO upAndDownvoteDTO) {
		return bloggerDao.upAndDownvote(upAndDownvoteDTO);
	}

	public List<BlogDTO> findAllBLoggerPostUserName(String username) {
		List<BlogDTO> blogDTOs = bloggerDao.findAllBLoggerPostUserName(username);
		Collections.reverse(blogDTOs);
		return blogDTOs;
	}

	public BaseResponse approvedUpAndDownvote(UpAndDownvoteDTO upAndDownvoteDTO) {
		return bloggerDao.approvedUpAndDownvote(upAndDownvoteDTO);
	}

}
