package com.ahasan.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ahasan.rest.entity.UpAndDownvote;

public interface UpAndDownVoteRepository extends JpaRepository<UpAndDownvote, Long> {

	@Modifying
	@Query("UPDATE UpAndDownvote v set v.approved = ?2 where v.upAndDownVoteId = ?1")
	void approvePaddingVote(Long upAndDownVoteId, int approved);

	UpAndDownvote findByBlog_BlogIdAndUser_Id(Long blogId, Integer id);

}
