package com.murebackend.murebackend.User;


public interface UserRepository {
	int save(User user);
	int update(User user);
	User findByEmail(String email);
}
