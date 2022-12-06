package com.murebackend.murebackend.User;


import com.murebackend.murebackend.Role.Role;

import java.util.List;

public interface UserRepository {
	void save(User user);
	int update(User user);
	void updateImage(User user, String image);
	User findByEmail(String email);
	int addRole(Role role, User user);
	List<Role> getUserRoles(User user);
}
