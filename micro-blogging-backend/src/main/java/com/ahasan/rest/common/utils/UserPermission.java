package com.ahasan.rest.common.utils;

public enum UserPermission {

	CREATE("CREATE_PROFILE", 1),
	READ("READ_PROFILE", 2),
	UPDATE("UPDATE_PROFILE", 3),
	DELETE("DELETE_PROFILE", 4);
	
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
