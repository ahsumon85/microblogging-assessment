package com.ahasan.rest.dto;

import java.io.Serializable;

/**
 *
 * @author Ahasan Habib
 * @since 03 06 20
 */

public class UserDTO implements Serializable {

	private static final long serialVersionUID = 7657451394244852266L;

	private Integer id;

	private String username;

	private String password;

	private String email;

	private RoleDTO role;

	private boolean enabled;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public RoleDTO getRole() {
		return role;
	}

	public void setRole(RoleDTO role) {
		this.role = role;
	}

}
