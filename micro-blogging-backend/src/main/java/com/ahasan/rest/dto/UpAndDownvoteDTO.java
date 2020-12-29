package com.ahasan.rest.dto;

public class UpAndDownvoteDTO {

	private Long upAndDownVoteId;

	private Integer upvote;

	private Integer downvote;
	
	private int approved;

	private BlogDTO blog;

	private UserDTO user;

	public Long getUpAndDownVoteId() {
		return upAndDownVoteId;
	}

	public void setUpAndDownVoteId(Long upAndDownVoteId) {
		this.upAndDownVoteId = upAndDownVoteId;
	}

	public Integer getUpvote() {
		return upvote;
	}

	public void setUpvote(Integer upvote) {
		this.upvote = upvote;
	}

	public Integer getDownvote() {
		return downvote;
	}

	public void setDownvote(Integer downvote) {
		this.downvote = downvote;
	}

	public BlogDTO getBlog() {
		return blog;
	}

	public void setBlog(BlogDTO blog) {
		this.blog = blog;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

}
