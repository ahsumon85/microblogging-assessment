package com.ahasan.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ahasan.rest.common.utils.ApplicationUtils;
import com.ahasan.rest.entity.Oauthclientdetails;
import com.ahasan.rest.entity.Permission;
import com.ahasan.rest.entity.Role;
import com.ahasan.rest.entity.RolePermission;
import com.ahasan.rest.entity.RolePermissionId;
import com.ahasan.rest.entity.RoleUser;
import com.ahasan.rest.entity.RoleUserId;
import com.ahasan.rest.entity.User;
import com.ahasan.rest.repo.OauthClientDetailsRepository;
import com.ahasan.rest.repo.PermissionRepository;
import com.ahasan.rest.repo.RolePermissionRepo;
import com.ahasan.rest.repo.RoleRepo;
import com.ahasan.rest.repo.RoleUserRepo;
import com.ahasan.rest.repo.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private OauthClientDetailsRepository oauthClientDetailsRepository;

	@Autowired
	private PermissionRepository permissionRepository;

	@Autowired
	private RolePermissionRepo rolePermissionRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleUserRepo roleUserRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {

		if (oauthClientDetailsRepository.findAll().isEmpty()) {
			Oauthclientdetails oauthclientdetails = new Oauthclientdetails();
			oauthclientdetails.setClientId("client-id");
			oauthclientdetails.setClientSecret(ApplicationUtils.provideEncodePassword("client-pass"));
			oauthclientdetails.setScope("READ,WRITE");
			oauthclientdetails.setAccessTokenValidity(3600);
			oauthclientdetails.setRefreshTokenValidity(10000);
			oauthclientdetails.setResourceIds("microservice");
			oauthclientdetails.setAuthorizedGrantTypes("authorization_code,password,refresh_token,implicit");
			oauthclientdetails.setAdditionalInformation("{}");
			oauthClientDetailsRepository.save(oauthclientdetails);
		}

		if (permissionRepository.findAll().isEmpty()) {
			String access[] = { "CREATE", "READ", "UPDATE", "DELETE" };
			for (int i = 0; i < access.length; i++) {
				Permission permission = new Permission();
				permission.setName(access[i]);
				permissionRepository.save(permission);
			}
		}

		if (roleRepo.findAll().isEmpty()) {
			String roles[] = { "ADMIN", "BLOGGER", "GUEST" };
			for (int i = 0; i < roles.length; i++) {
				Role role = new Role();
				role.setName(roles[i]);
				roleRepo.save(role);
			}
		}

		if (rolePermissionRepo.findAll().isEmpty()) {
			List<Role> roles = roleRepo.findAll();
			List<Permission> permissions = permissionRepository.findAll();
			for (Role role : roles) {
				if (role.getName().equals("ADMIN")) {
					for (Permission permission : permissions) {
						RolePermission rolePermission = new RolePermission();
						RolePermissionId rolePermissionId = new RolePermissionId(role.getId(), permission.getId());
						rolePermission.setId(rolePermissionId);
						rolePermission.setRole(role);
						rolePermission.setPermission(permission);
						rolePermissionRepo.save(rolePermission);
					}
				}

				if (role.getName().equals("BLOGGER")) {
					for (Permission permission : permissions) {
						RolePermission rolePermission = new RolePermission();
						if (!permission.getName().equals("DELETE")) {
							RolePermissionId rolePermissionId = new RolePermissionId(role.getId(), permission.getId());
							rolePermission.setId(rolePermissionId);
							rolePermission.setRole(role);
							rolePermission.setPermission(permission);
							rolePermissionRepo.save(rolePermission);
						}
					}
				}

				if (role.getName().equals("GUEST")) {
					for (Permission permission : permissions) {
						RolePermission rolePermission = new RolePermission();
						if (permission.getName().equals("READ")) {
							RolePermissionId rolePermissionId = new RolePermissionId(role.getId(), permission.getId());
							rolePermission.setId(rolePermissionId);
							rolePermission.setRole(role);
							rolePermission.setPermission(permission);
							rolePermissionRepo.save(rolePermission);
						}
					}
				}
			}
		}

		if (userRepository.findAll().isEmpty()) {
			User user = new User();
			user.setUsername("admin");
			user.setPassword(ApplicationUtils.provideEncodePassword("admin"));
			user.setEmail("ahasan@gmail.com");
			user.setEnabled(true);
			user.setAccountNonExpired(true);
			user.setCredentialsNonExpired(true);
			user.setAccountNonLocked(true);
			userRepository.save(user);
		}
		if (roleUserRepo.findAll().isEmpty()) {
			List<User> users = userRepository.findAll();
			List<Role> roles = roleRepo.findAll();
			for (User user : users) {
				if (user.getUsername().equals("admin")) {
					for (Role role : roles) {
						if (role.getName().equals("ADMIN")) {
							RoleUser roleUser = new RoleUser();
							RoleUserId roleUserId = new RoleUserId(role.getId(), user.getId());
							roleUser.setId(roleUserId);
							roleUser.setRole(role);
							roleUser.setUser(user);
							roleUserRepo.save(roleUser);
						}
					}
				}
			}
		}
	}
}
