package com.murebackend.murebackend.User;


public interface UserRepository {
	int save(User user);
	int update(User user);
	User findById(Long id);
	User findByEmail(String email);
}
