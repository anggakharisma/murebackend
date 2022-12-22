package com.murebackend.murebackend.User;


import java.util.List;

import com.murebackend.murebackend.Role.Role;

public interface UserRepository {
	void save(User user);
	int update(User user);
	void updateImage(User user, String image);
	User findByEmail(String email);
	int addRole(int roleId, int userId);
	List<Role> getUserRoles(User user);
}
