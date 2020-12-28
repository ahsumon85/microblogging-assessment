package com.ahasan.rest.repo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ahasan.rest.entity.Blog;
import com.ahasan.rest.entity.Permission;


public interface PermissionRepository extends JpaRepository<Permission, Integer> {


}
