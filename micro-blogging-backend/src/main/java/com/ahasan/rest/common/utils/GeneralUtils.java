package com.ahasan.rest.common.utils;

import org.springframework.beans.BeanUtils;

import com.ahasan.rest.dto.BlogDTO;
import com.ahasan.rest.dto.CommentDTO;
import com.ahasan.rest.dto.RoleDTO;
import com.ahasan.rest.dto.UpAndDownvoteDTO;
import com.ahasan.rest.dto.UserDTO;
import com.ahasan.rest.entity.Blog;
import com.ahasan.rest.entity.Comment;
import com.ahasan.rest.entity.UpAndDownvote;
import com.ahasan.rest.entity.User;

public class GeneralUtils {

	public static UserDTO provideUserToUserDto(User user) {
		UserDTO userDTO = new UserDTO();
		RoleDTO roleDTO = new RoleDTO();
		BeanUtils.copyProperties(user, userDTO);
		BeanUtils.copyProperties(user.getRoles().stream().findFirst().orElse(null), roleDTO);
		userDTO.setRole(roleDTO);
		return userDTO;
	}

	public static Comment copyCommentDtoToEntity(CommentDTO commentDTO) {
		Comment comment = new Comment();
		BeanUtils.copyProperties(commentDTO, comment);
		comment.setBlog(provideBlogByBlogId(commentDTO.getBlog().getBlogId()));
		comment.setUser(provideUserByUserId(commentDTO.getUser().getId()));
		return comment;
	}
	
	public static CommentDTO copyCommentDtoToEntity(Comment comment) {
		CommentDTO commentDTO = new CommentDTO();
		BeanUtils.copyProperties(commentDTO, comment);
		commentDTO.setUser(provideUserToUserDto(comment.getUser()));
		return commentDTO;
	}

	public static UpAndDownvoteDTO provideUpAndDownvoteEntityToDto(UpAndDownvote upAndDownvote) {
		UpAndDownvoteDTO upAndDownvoteDTO = new UpAndDownvoteDTO();
		BeanUtils.copyProperties(upAndDownvote, upAndDownvoteDTO);
//		upAndDownvoteDTO.setBlog( provideBlogEntityToDto(upAndDownvote.getBlog()));
		upAndDownvoteDTO.setUser(provideUserToUserDto(upAndDownvote.getUser()));
		return upAndDownvoteDTO;
	}
	
	public static BlogDTO provideBlogEntityToDto(Blog blog) {
		BlogDTO blogDTO = new BlogDTO();
		BeanUtils.copyProperties(blog, blogDTO);
		return blogDTO;
	}

	public static UpAndDownvote copyUpAndDownvoteDtoToEntity(UpAndDownvoteDTO upAndDownvoteDTO) {
		UpAndDownvote upAndDownvote = new UpAndDownvote();
		BeanUtils.copyProperties(upAndDownvoteDTO, upAndDownvote);
		upAndDownvote.setUser(provideUserByUserId(upAndDownvoteDTO.getUser().getId()));
		upAndDownvote.setBlog(provideBlogByBlogId(upAndDownvoteDTO.getBlog().getBlogId()));
		return upAndDownvote;
	}

	public static Blog copyBlogDtoToBlog(BlogDTO blogDTO) {
		Blog blog = new Blog();
		BeanUtils.copyProperties(blogDTO, blog);
		blog.setPublish(Status.ACTIVE.getCode());
		blog.setContentTitle(ApplicationUtils.convertCamelcaseString(blogDTO.getContentTitle()));
		return blog;
	}

	public static User provideUserByUserId(Integer userId) {
		User user = new User();
		user.setId(userId);
		return user;
	}

	public static Blog provideBlogByBlogId(Long blogId) {
		Blog blog = new Blog();
		blog.setBlogId(blogId);
		return blog;
	}

}
