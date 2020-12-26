package com.ahasan.rest.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ahasan.rest.entity.Blog;


public interface BlogRepository extends JpaRepository<Blog, Long> {

	List<Blog> findByActiveStatusAndPublish(int status, int publish);

//	@Modifying
//	@Query("UPDATE Blog b set b.activeStatus = ?2,  b.publish = ?3 where b.blogId = ?1")
//	void approveAbdPublishPost(Long blogId, int activeStatus, int publish);
//
//
//	boolean existsByBlogId(long blogId);
//
//	void deleteByBlogIdAndUsers_Id(long blogId, int userId);
//
//	void deleteByBlogId(long blogId);



}
