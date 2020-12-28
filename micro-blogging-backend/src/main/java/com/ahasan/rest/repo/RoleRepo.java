package com.ahasan.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahasan.rest.entity.Role;

/**
 *
 * @author Ahasan Habib
 * @since 03 06 20
 */

public interface RoleRepo extends JpaRepository<Role, Integer> {

	Role findByName(String role);


}
