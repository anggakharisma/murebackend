package com.murebackend.murebackend.User;

import java.util.List;

import com.murebackend.murebackend.Role.Role;

public interface UserRepository {
	Long save(User user, String password);

	int update(User user);

	void updateImage(User user, String image);

	User findByEmail(String email);

	int addRole(Long roleId, Long userId);

	List<Role> getUserRoles(User user);
}
