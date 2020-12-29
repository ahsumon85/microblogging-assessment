package com.ahasan.rest.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahasan.rest.entity.Blog;


public interface BlogRepository extends JpaRepository<Blog, Long> {
	
	List<Blog> findByPublish(int publish);


}
