package com.ahasan.rest.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "permission_role")
public class PermissionRole {

	@EmbeddedId
	private PermissionRoleId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("permissionId")
	private Permission permission;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("roleId")
	private Role role;

	public PermissionRole() {
	}

	public PermissionRole(PermissionRoleId id, Permission permission, Role role) {
		this.id = new PermissionRoleId(permission.getId(), role.getId());
		this.permission = permission;
		this.role = role;
	}

	public PermissionRoleId getId() {
		return id;
	}

	public void setId(PermissionRoleId id) {
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
