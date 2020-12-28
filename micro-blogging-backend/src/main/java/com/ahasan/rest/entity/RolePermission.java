package com.ahasan.rest.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "permission_role")
public class RolePermission {

	@EmbeddedId
	private RolePermissionId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("roleId")
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("permissionId")
	private Permission permission;

	public RolePermission() {

	}

	public RolePermission(RoleUserId id, Role role, Permission permission) {
		this.id = new RolePermissionId(role.getId(), permission.getId());
		this.role = role;
		this.permission = permission;
	}

	
	public RolePermissionId getId() {
		return id;
	}

	public void setId(RolePermissionId id) {
		this.id = id;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
