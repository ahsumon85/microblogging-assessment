package com.ahasan.rest.common.utils;

public enum UserPermission {

	CREATE("CREATE", 1),
	READ("READ", 2),
	UPDATE("UPDATE", 3),
	DELETE("DELETE", 4);
	
	private final String permission;

	private final Integer permissionId;
	
	
	private UserPermission(String permission, Integer permissionId) {
		this.permission = permission;
		this.permissionId = permissionId;
	}

	public String getPermission() {
		return permission;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	
}
