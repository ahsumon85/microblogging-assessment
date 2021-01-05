package com.ahasan.rest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PermissionRoleId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "permission_id")
	private Integer permissionId;

	@Column(name = "role_id")
	private Integer roleId;

	public PermissionRoleId() {
		super();
	}

	public PermissionRoleId(Integer permissionId, Integer roleId) {
		this.permissionId = permissionId;
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

}
