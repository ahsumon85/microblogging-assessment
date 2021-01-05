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
@Table(name = "role_user")
public class RoleUser {

	@EmbeddedId
	private RoleUserId id;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("roleId")
	private Role role;

	@ManyToOne(fetch = FetchType.LAZY)
	@MapsId("userId")
	private User user;

	public RoleUser() {

	}

	public RoleUser(RoleUserId id, Role role, User user) {
		this.id = new RoleUserId(role.getId(), user.getId());
		this.role = role;
		this.user = user;
	}

	public RoleUserId getId() {
		return id;
	}

	public void setId(RoleUserId id) {
		this.id = id;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
