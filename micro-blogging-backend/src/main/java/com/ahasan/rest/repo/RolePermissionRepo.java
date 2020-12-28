package com.ahasan.rest.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ahasan.rest.entity.RolePermission;
import com.ahasan.rest.entity.RolePermissionId;

/**
 *
 * @author Ahasan Habib
 * @since 03 06 20
 */

public interface RolePermissionRepo extends JpaRepository<RolePermission, RolePermissionId> {


}
