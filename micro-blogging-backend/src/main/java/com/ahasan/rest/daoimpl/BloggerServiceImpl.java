package com.ahasan.rest.daoimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.common.messages.CustomMessage;
import com.ahasan.rest.common.utils.ApplicationUtils;
import com.ahasan.rest.common.utils.Status;
import com.ahasan.rest.dao.BloggerDao;
import com.ahasan.rest.dto.BlogDTO;
import com.ahasan.rest.dto.UserDTO;
import com.ahasan.rest.entity.Blog;
import com.ahasan.rest.entity.User;
import com.ahasan.rest.repo.BlogRepository;


@Service

public class BloggerServiceImpl extends BloggerDao {

	@Autowired
	private BlogRepository blogRepository;
	
//	@Autowired
//	private CommentRepository commentRepository;
//	
//	@Autowired
//	private LikeAndDislikeRepository likeAndDislikeRepository;


	@Transactional
	public BaseResponse createBlogPostByBlogger(@Valid BlogDTO blogDTO) {
		Blog blog = copyBlogDtoToBlog(blogDTO);
		blogRepository.save(blog);
		return new BaseResponse(CustomMessage.BLOG_POST_SUCCESS);
	}

//	
//	
//	@Override
//	public BaseResponse deleteOwnBlogPostById(int userId, long blogId) {
//		if (blogRepository.existsByBlogId((int) blogId)) {
//			likeAndDislikeRepository.deleteByBlog_BlogId(blogId);
//			commentRepository.deleteByBlog_BlogId(blogId);
//			blogRepository.deleteByBlogIdAndUsers_Id(blogId, userId);
//		} else {
//			throw new RecordNotFoundException(CustomMessage.NO_RECOURD_FOUND + blogId);
//		}
//		return new BaseResponse(CustomMessage.BLOG_POST_DELETE);
//	}
//
//	
	public List<BlogDTO> findAllApproedBloggerPost(int status, int publish) {
		List<Blog> findApprovedBloggerPost = blogRepository.findByActiveStatusAndPublish(status, publish);
//		List<LikeAndDislike> postLikeDislikeList = likeAndDislikeRepository.findAll();
//		List<Comment> psotCommentList = commentRepository.findAll();
		return findApprovedBloggerPost.stream().map(blog -> provideBlogToBlogDto(blog)).collect(Collectors.toList());
	}	
//	
//	
//	
//	public BaseResponse likeAndDislikeOtherApprvPost(LikeAndDislikeDTO likeAndDislikeDTO) {
//		LikeAndDislike likeAndDislike = copyLikeAndDislikeDtoToEntity(likeAndDislikeDTO);
//		likeAndDislikeRepository.save(likeAndDislike);
//		return new BaseResponse(CustomMessage.LIKE_SUCCESS);
//	}
//	
//	
//	private LikeAndDislike copyLikeAndDislikeDtoToEntity(LikeAndDislikeDTO likeAndDislikeDTO) {
//		LikeAndDislike likeAndDislike = new LikeAndDislike();
//		LikeAndDislike recordedLikeAndDis = likeAndDislikeRepository.findByBlog_BlogId(likeAndDislikeDTO.getBlog().getBlogId());
//		BeanUtils.copyProperties(likeAndDislikeDTO, likeAndDislike);
//		likeAndDislike.setBlog(provideBlogByBlogId(likeAndDislikeDTO.getBlog().getBlogId()));
//		likeAndDislike.setLikeDislikeId(recordedLikeAndDis != null ? recordedLikeAndDis.getLikeDislikeId() : null);
//		if(likeAndDislikeDTO.getLikes() == StatusValue.LIKE.getValue()) {
//			
//			likeAndDislike.setDislikes(recordedLikeAndDis != null ? recordedLikeAndDis.getDislikes() : 0);
//			int like = recordedLikeAndDis != null ? recordedLikeAndDis.getLikes() + 1 : likeAndDislikeDTO.getLikes();
//			likeAndDislike.setLikes(like);
//		}else {
//			int dislike = recordedLikeAndDis != null ? recordedLikeAndDis.getDislikes() + 1 : likeAndDislikeDTO.getDislikes();
//			likeAndDislike.setDislikes(dislike);
//			likeAndDislike.setLikes(recordedLikeAndDis != null ? recordedLikeAndDis.getLikes() : 0);
//		}
//		return likeAndDislike;
//	}
//
//	
//	
//	
//	
//	
//	public BaseResponse commentOtherApprovedPost(CommentDTO commentDTO) {
//		Comment comment = copyCommentDtoToEntity(commentDTO);
//		commentRepository.save(comment);
//		return new BaseResponse(CustomMessage.COMMENT_SUCCESS);
//	}
//	
//	
//	
//	
//	private Comment copyCommentDtoToEntity(CommentDTO commentDTO) {
//		Comment comment = new Comment();
//		BeanUtils.copyProperties(commentDTO, comment);
//		comment.setBlog(provideBlogByBlogId(commentDTO.getBlog().getBlogId()));
//		comment.setUsers(provideUserByUserId(commentDTO.getUsers().getUserId()));
//		return comment;
//	}
//
//	
//	
//	
	private Blog copyBlogDtoToBlog(@Valid BlogDTO blogDTO) {
		Blog blog = new Blog();
		BeanUtils.copyProperties(blogDTO, blog);
		blog.setPublish(Status.ACTIVE.getCode());
		blog.setActiveStatus(Status.ACTIVE.getCode());
		blog.setUser(provideUserByUserId(blogDTO.getUser().getId()));
		return blog;
	}

	public User provideUserByUserId(Integer userId) {
		User user = new User();
		user.setId(userId);
		return user;
	}
//	
//	
//	
//	public Blog provideBlogByBlogId(Long blogId) {
//		Blog blog = new Blog();
//		blog.setBlogId(blogId);
//		return blog;
//	}
//
//	
//	
//	public Blog provideBlogDtoToBlog(BlogDTO blogDTO) {
//		Blog blog = new Blog();
//		BeanUtils.copyProperties(blogDTO, blog);
//		return blog;
//	}
//
//	
//	
	public BlogDTO provideBlogToBlogDto(Blog blog) {
		BlogDTO blogDTO = new BlogDTO();
		UserDTO userDTO = new UserDTO();
//		LikeAndDislikeDTO likeAndDislikeDTO = new LikeAndDislikeDTO();
//		List<Comment> commentListByBlogId = psotCommentList.stream().filter(com -> com.getBlog().getBlogId() == blog.getBlogId()).collect(Collectors.toList());
//		LikeAndDislike likeDislikeByBlogId = postLikeDislikeList.stream().filter(likeDislike -> likeDislike.getBlog().getBlogId() == blog.getBlogId()).findFirst().orElse(null);
		BeanUtils.copyProperties(blog, blogDTO);
		BeanUtils.copyProperties(blog.getUser(), userDTO);
//		if(likeDislikeByBlogId == null) {
//			likeAndDislikeDTO = new LikeAndDislikeDTO();
//		}else {
//			BeanUtils.copyProperties(likeDislikeByBlogId, likeAndDislikeDTO);
//		}
//		likeAndDislikeDTO.setBlog(null);
		blogDTO.setUser(userDTO);
		blogDTO.setCreateDate(ApplicationUtils.convertDateToLocalDateTime(blog.getCreateDate()));	
//		List<CommentDTO> provideCommentDTO = commentListByBlogId.stream().map(comment -> {
//			CommentDTO commentDTO = new CommentDTO();
//			BeanUtils.copyProperties(comment, commentDTO);
//			commentDTO.setUsers(null);
//			commentDTO.setBlog(null);
//			return commentDTO;
//		}).collect(Collectors.toList());
//		
//		blogDTO.setBloggerComment(provideCommentDTO);
//		blogDTO.setBloggerLikeDislikes(likeAndDislikeDTO);
		return blogDTO;
	}

}