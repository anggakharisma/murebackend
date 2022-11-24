package com.murebackend.murebackend.User;


public interface UserRepository {
	int save(User user);
	int update(User user);
	int updateImage(User user, String image);
	User findByEmail(String email);
}
