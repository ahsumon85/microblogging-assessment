package com.ahasan.rest.common.utils;

public enum UserRole {

	ADMIN("ADMIN", 1),
	BLOGGER("BLOGGER", 2),
	GUEST("GUEST", 3);
	
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
