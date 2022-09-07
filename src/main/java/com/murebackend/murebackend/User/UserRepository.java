package com.murebackend.murebackend.User;

import java.util.List;

public interface UserRepository {
	int save(User user);
	int update(User user);
	User findById(Long id);
	User findByEmail(String email);
	List<User> findAll();
}
