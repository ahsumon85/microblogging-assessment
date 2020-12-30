package com.ahasan.rest.dao;

import java.util.List;

import javax.validation.Valid;

import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.dto.BlogDTO;
import com.ahasan.rest.dto.UpAndDownvoteDTO;


public abstract class BloggerDao {

	public abstract BaseResponse  createBlogPostByBlogger(@Valid BlogDTO blogDTO);

//	public BaseResponse deleteOwnBlogPostById(int userId, long blogId);
//	
	public abstract List<BlogDTO> findAllBloggerPostByStatus(int publish, Integer status);

	public abstract BaseResponse upAndDownvote(UpAndDownvoteDTO upAndDownvoteDTO);
	
	public abstract BaseResponse deleteOwnBlogPostById(Long blogId);
	
	public abstract List<BlogDTO> findAllBLoggerPostUserName(String username);

	public abstract BaseResponse approvedUpAndDownvote(UpAndDownvoteDTO upAndDownvoteDTO);
	
//	public BaseResponse likeAndDislikeOtherApprvPost(LikeAndDislikeDTO likeAndDislikeDTO);
//	
//	public BaseResponse commentOtherApprovedPost(CommentDTO commentDTO);
}
