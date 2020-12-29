package com.ahasan.rest.dto;

import java.util.List;

public class BlogDTO {

	private Long blogId;

	private String contentTitle;

	private String content;

	private Integer publish;

	private String createDate;

	private Long totalUpVote;

	private Long totaldownVote;

	private List<UpAndDownvoteDTO> upAndDownvote;;
//
	private List<CommentDTO> bloggerComment;

	private UserDTO user;

	public Long getBlogId() {
		return blogId;
	}

	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPublish() {
		return publish;
	}

	public void setPublish(Integer publish) {
		this.publish = publish;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<UpAndDownvoteDTO> getUpAndDownvote() {
		return upAndDownvote;
	}

	public void setUpAndDownvote(List<UpAndDownvoteDTO> upAndDownvote) {
		this.upAndDownvote = upAndDownvote;
	}

	public Long getTotalUpVote() {
		return totalUpVote;
	}

	public void setTotalUpVote(Long totalUpVote) {
		this.totalUpVote = totalUpVote;
	}

	public Long getTotaldownVote() {
		return totaldownVote;
	}

	public void setTotaldownVote(Long totaldownVote) {
		this.totaldownVote = totaldownVote;
	}

	public String getContentTitle() {
		return contentTitle;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public List<CommentDTO> getBloggerComment() {
		return bloggerComment;
	}

	public void setBloggerComment(List<CommentDTO> bloggerComment) {
		this.bloggerComment = bloggerComment;
	}

}
