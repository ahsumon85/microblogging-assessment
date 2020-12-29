package com.ahasan.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahasan.rest.entity.UpAndDownvote;

public interface UpAndDownVoteRepository extends JpaRepository<UpAndDownvote, Long> {



}
