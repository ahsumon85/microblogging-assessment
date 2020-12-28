package com.ahasan.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahasan.rest.entity.Oauthclientdetails;

public interface OauthClientDetailsRepository extends JpaRepository<Oauthclientdetails, String> {

}
