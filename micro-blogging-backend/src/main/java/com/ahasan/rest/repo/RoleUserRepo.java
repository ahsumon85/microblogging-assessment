package com.ahasan.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahasan.rest.entity.RoleUser;
import com.ahasan.rest.entity.RoleUserId;

/**
 *
 * @author Ahasan Habib
 * @since 03 06 20
 */

public interface RoleUserRepo extends JpaRepository<RoleUser, RoleUserId> {


}
