package com.ahasan.rest.common.utils;

public enum Status {
	
	ACTIVE("Active", 1),
	
	INACTIVE("Inactive", 0);

	private final String name;

	private final int code;

	private Status(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}

}
