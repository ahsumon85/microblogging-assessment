package com.ahasan.rest.daoimpl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ahasan.rest.common.exceptions.CustomDataIntegrityViolationException;
import com.ahasan.rest.common.messages.BaseResponse;
import com.ahasan.rest.common.messages.CustomMessage;
import com.ahasan.rest.common.utils.ApplicationUtils;
import com.ahasan.rest.common.utils.GeneralUtils;
import com.ahasan.rest.common.utils.Topic;
import com.ahasan.rest.common.utils.UserRole;
import com.ahasan.rest.dao.UserDao;
import com.ahasan.rest.dto.UserDTO;
import com.ahasan.rest.entity.Permission;
import com.ahasan.rest.entity.Role;
import com.ahasan.rest.entity.RoleUser;
import com.ahasan.rest.entity.RoleUserId;
import com.ahasan.rest.entity.User;
import com.ahasan.rest.repo.RoleRepo;
import com.ahasan.rest.repo.RoleUserRepo;
import com.ahasan.rest.repo.UserRepository;

@Service

public class UserDaompl extends UserDao {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleUserRepo roleUserRepo;
	
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDTO getUserInfoByUserName(String username) {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username or password wrong"));
		return GeneralUtils.provideUserToUserDto(optionalUser.get());
	}

	@Transactional
	public BaseResponse bloggerSignUp(UserDTO userDTO) {
		try {
			User user = provideUserInfoFrmUserDto(userDTO);
			userRepository.save(user);
			RoleUser roleUser = provideUserRoleFrmUser(user);
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

	public RoleUser provideUserRoleFrmUser(User user) {
		RoleUser roleUser = new RoleUser();
		Role role = roleRepo.findByName(UserRole.BLOGGER.getRole());
		RoleUserId roleUserId = new RoleUserId(role.getId(), user.getId());
		roleUser.setId(roleUserId);
		roleUser.setRole(role);
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
