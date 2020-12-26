package com.ahasan.rest.dao;

import java.util.List;

import javax.validation.Valid;

import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.dto.BlogDTO;


public abstract class BloggerDao {

	public abstract BaseResponse  createBlogPostByBlogger(@Valid BlogDTO blogDTO);

//	public BaseResponse deleteOwnBlogPostById(int userId, long blogId);
//	
	public abstract List<BlogDTO> findAllApproedBloggerPost(int status, int publish);
	
//	public BaseResponse likeAndDislikeOtherApprvPost(LikeAndDislikeDTO likeAndDislikeDTO);
//	
//	public BaseResponse commentOtherApprovedPost(CommentDTO commentDTO);
}
