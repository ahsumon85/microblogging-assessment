package com.ahasan.rest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "upvote_downvote")
public class UpAndDownvote {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "up_down_vote_id")
	private Long upAndDownVoteId;

	@Column(name = "upvote")
	private Integer upvote;

	@Column(name = "downvote")
	private Integer downvote;

	@Column(name = "approved")
	private int approved;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "blog_id")
	private Blog blog;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private User user;

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

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}

}
