package com.murebackend.murebackend.User;

import java.util.List;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcUserRepository implements UserRepository {

	private JdbcTemplate jdbcTemplate;

	@Override
	public int save(User user) {
		return jdbcTemplate.update("INSERT INTO users (name, email, password) VALUES(?,?,?)",
        new Object[] { user.getName(), user.getEmail(), user.getPassword() });
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findByEmail(String email) {
		try {
		User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?", BeanPropertyRowMapper.newInstance(User.class), email);
		return user;
		} catch(IncorrectResultSizeDataAccessException e)  { 
			return null; 
		}
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
