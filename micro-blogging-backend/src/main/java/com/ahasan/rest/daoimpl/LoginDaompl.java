package com.ahasan.rest.daoimpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ahasan.rest.common.exceptions.CustomDataIntegrityViolationException;
import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.common.messages.CustomMessage;
import com.ahasan.rest.common.utils.ApplicationUtils;
import com.ahasan.rest.common.utils.Topic;
import com.ahasan.rest.common.utils.UserRole;
import com.ahasan.rest.dao.LoginDao;
import com.ahasan.rest.dto.UserDTO;
import com.ahasan.rest.entity.Permission;
import com.ahasan.rest.entity.Role;
import com.ahasan.rest.entity.RoleUser;
import com.ahasan.rest.entity.RoleUserId;
import com.ahasan.rest.entity.User;
import com.ahasan.rest.repo.RoleUserRepo;
import com.ahasan.rest.repo.UserRepository;

@Service

public class LoginDaompl extends LoginDao {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleUserRepo roleUserRepo;


	@Transactional
	public BaseResponse bloggerSignUp(UserDTO userDTO) {
		try {
			User user = provideUserInfoFrmUserDto(userDTO);
			userRepository.save(user);

			RoleUser roleUser = provideUserRole(user);
			roleUserRepo.save(roleUser);
		} catch (DataIntegrityViolationException ex) {
			throw new CustomDataIntegrityViolationException(ex.getCause().getCause().getMessage());
		}
		return new BaseResponse(Topic.USER.getName() + CustomMessage.SIGNUP_SUCCESS_MESSAGE);
	}
	
	
	public User provideUserInfoFrmUserDto(UserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(ApplicationUtils.provideEncodePassword(userDTO.getPassword()));
		user.setEmail(userDTO.getEmail());
		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setCredentialsNonExpired(true);
		user.setAccountNonLocked(true);
		return user;
	}

	public RoleUser provideUserRole(User user) {
		RoleUser roleUser = new RoleUser();
		RoleUserId roleUserId = new RoleUserId(provideRoleByRoleId(UserRole.EDITOR.getValue()).getId(), user.getId());
		roleUser.setId(roleUserId);
		roleUser.setRole(provideRoleByRoleId(UserRole.EDITOR.getValue()));
		roleUser.setUser(user);
		return roleUser;
	}

	public Permission providePermissionById(Integer permisssionId) {
		Permission permission = new Permission();
		permission.setId(permisssionId);
		return permission;
	}

	public Role provideRoleByRoleId(Integer roleId) {
		Role role = new Role();
		role.setId(roleId);
		return role;
	}
}
