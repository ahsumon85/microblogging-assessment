package com.ahasan.rest.repo;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ahasan.rest.entity.Comment;



public interface CommentRepository extends JpaRepository<Comment, Integer> {

	void deleteByBlog_BlogId(long blogId);

}
