package com.ahasan.rest.daoimpl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahasan.rest.common.exceptions.RecordNotFoundException;
import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.common.messages.CustomMessage;
import com.ahasan.rest.common.utils.ApplicationUtils;
import com.ahasan.rest.common.utils.GeneralUtils;
import com.ahasan.rest.common.utils.Status;
import com.ahasan.rest.dao.BloggerDao;
import com.ahasan.rest.dto.BlogDTO;
import com.ahasan.rest.dto.CommentDTO;
import com.ahasan.rest.dto.UpAndDownvoteDTO;
import com.ahasan.rest.dto.UserDTO;
import com.ahasan.rest.entity.Blog;
import com.ahasan.rest.entity.Comment;
import com.ahasan.rest.entity.UpAndDownvote;
import com.ahasan.rest.entity.User;
import com.ahasan.rest.repo.BlogRepository;
import com.ahasan.rest.repo.CommentRepository;
import com.ahasan.rest.repo.UpAndDownVoteRepository;
import com.ahasan.rest.repo.UserRepository;

@Service
public class BloggerServiceImpl extends BloggerDao {

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UpAndDownVoteRepository upAndDownVoteRepository;

	@Transactional
	public BaseResponse createBlogPostByBlogger(BlogDTO blogDTO) {
		Blog blog = GeneralUtils.copyBlogDtoToBlog(blogDTO);
		User user = userRepository.findByUsername(ApplicationUtils.provideCurrentUserName()).get();
		blog.setUser(user);
		blogRepository.save(blog);
		return new BaseResponse(CustomMessage.BLOG_POST_SUCCESS);
	}

	public List<BlogDTO> findAllBLoggerPostUserName(String username) {
		List<Blog> bloggerPostList = blogRepository.findByUser_Username(username);
		List<UpAndDownvote> upAndDownvoteList = upAndDownVoteRepository.findAll();
		List<Comment> blogComment = commentRepository.findAll();
		return bloggerPostList.stream()
				.map(blog -> provideBlogToBlogDto(blog, upAndDownvoteList, blogComment, Status.ACTIVE.getCode()))
				.collect(Collectors.toList());
	}

	public BaseResponse deleteOwnBlogPostById(Long blogId) {
		if (blogRepository.existsById(blogId)) {
//			likeAndDislikeRepository.deleteByBlog_BlogId(blogId);
//			commentRepository.deleteByBlog_BlogId(blogId);
			blogRepository.deleteById(blogId);
		} else {
			throw new RecordNotFoundException(CustomMessage.NO_RECOURD_FOUND + blogId);
		}
		return new BaseResponse(CustomMessage.BLOG_POST_DELETE);
	}

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
	public List<BlogDTO> findAllBloggerPostByStatus(int publish, Integer active) {
		List<Blog> findApprovedBloggerPost = blogRepository.findByPublish(publish);
		List<UpAndDownvote> upAndDownvoteList = upAndDownVoteRepository.findAll();
		List<Comment> blogComment = commentRepository.findAll();
		return findApprovedBloggerPost.stream()
				.map(blog -> provideBlogToBlogDto(blog, upAndDownvoteList, blogComment, active))
				.collect(Collectors.toList());
	}

	public BlogDTO provideBlogToBlogDto(Blog blog, List<UpAndDownvote> upAndDownvoteList, List<Comment> blogComment,
			Integer active) {
		BlogDTO blogDTO = new BlogDTO();
		UserDTO userDTO = new UserDTO();
		List<CommentDTO> commentListByBlogId = blogComment.stream()
				.filter(com -> com.getBlog().getBlogId() == blog.getBlogId())
				.map(comment -> GeneralUtils.copyCommentDtoToEntity(comment)).collect(Collectors.toList());

		List<UpAndDownvoteDTO> currentUpAndDownvoteDTOs = upAndDownvoteList.stream()
				.filter(vote -> vote.getBlog().getBlogId() == blog.getBlogId())
				.filter(status -> status.getApproved() == active)
				.map(vodtDto -> GeneralUtils.provideUpAndDownvoteEntityToDto(vodtDto)).collect(Collectors.toList());

		BeanUtils.copyProperties(blog, blogDTO);
		BeanUtils.copyProperties(blog.getUser(), userDTO);
		blogDTO.setUser(userDTO);
		blogDTO.setCreateDate(ApplicationUtils.convertDateToLocalDateTime(blog.getCreateDate()));
		blogDTO.setUpAndDownvote(currentUpAndDownvoteDTOs);
		blogDTO.setBloggerComment(commentListByBlogId);

		List<UpAndDownvoteDTO> totalUpvoteDtos = currentUpAndDownvoteDTOs.stream().filter(x -> x.getUpvote() != null)
				.filter(vote -> vote.getUpvote() == Status.ACTIVE.getCode()).collect(Collectors.toList());
		Long totalUpVote = !totalUpvoteDtos.isEmpty() ? totalUpvoteDtos.stream().count() : null;

		List<UpAndDownvoteDTO> totalDownvoteDtos = currentUpAndDownvoteDTOs.stream()
				.filter(x -> x.getDownvote() != null).filter(vote -> vote.getDownvote() == Status.ACTIVE.getCode())
				.collect(Collectors.toList());
		Long totalDownVote = !totalDownvoteDtos.isEmpty() ? totalDownvoteDtos.stream().count() : null;
		blogDTO.setIsShowAdminBoard(!currentUpAndDownvoteDTOs.isEmpty() ? 1 : 0);
		blogDTO.setTotalUpVote(totalUpVote);
		blogDTO.setTotaldownVote(totalDownVote);
		return blogDTO;
	}

	public BaseResponse upAndDownvote(UpAndDownvoteDTO upAndDownvoteDTO) {
		UpAndDownvote upAndDownvote = upAndDownVoteRepository.findByBlog_BlogIdAndUser_Id(
				upAndDownvoteDTO.getBlog().getBlogId(), upAndDownvoteDTO.getUser().getId());
		if (upAndDownvote == null) {
			UpAndDownvote providedupAndDownvote = GeneralUtils.copyUpAndDownvoteDtoToEntity(upAndDownvoteDTO);
			providedupAndDownvote.setApproved(Status.INACTIVE.getCode());
			upAndDownVoteRepository.save(providedupAndDownvote);
		} else {
			if (upAndDownvote.getDownvote() != null) {
				upAndDownvote.setUpvote(Status.ACTIVE.getCode());
				upAndDownvote.setDownvote(null);
			} else {
				
				upAndDownvote.setDownvote(Status.ACTIVE.getCode());
				upAndDownvote.setUpvote(null);
			}
			upAndDownvote.setApproved(Status.INACTIVE.getCode());
			upAndDownVoteRepository.save(upAndDownvote);
		}

		return new BaseResponse(CustomMessage.VOTE_UNVOTE_MESSAGE);
	}

	@Transactional
	public BaseResponse approvedUpAndDownvote(UpAndDownvoteDTO upAndDownvoteDTO) {
		upAndDownVoteRepository.approvePaddingVote(upAndDownvoteDTO.getUpAndDownVoteId(), Status.ACTIVE.getCode());
		return new BaseResponse(CustomMessage.VOTE_UNVOTE_MESSAGE);
	}

//	public BaseResponse commentOtherApprovedPost(CommentDTO commentDTO) {
//		Comment comment = copyCommentDtoToEntity(commentDTO);
//		commentRepository.save(comment);
//		return new BaseResponse(CustomMessage.COMMENT_SUCCESS);
//	}
//	
//	
//	

}