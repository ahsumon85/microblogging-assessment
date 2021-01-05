package com.ahasan.rest.common.utils;

public enum UserRole {

	ADMIN("ROLE_ADMIN", 1),
	EDITOR("ROLE_EDITOR", 2),
	OPERATOR("ROLE_OPERATOR", 3);
	
	private final String role;

	private final int value;

	private UserRole(String role, int value) {
		this.role = role;
		this.value = value;
	}

	public String getRole() {
		return role;
	}

	public int getValue() {
		return value;
	}

}
