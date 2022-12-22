package com.murebackend.murebackend.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.murebackend.murebackend.Role.Role;

@Repository
public class JdbcUserRepository implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void save(User user) {
		jdbcTemplate.update("INSERT INTO users (name, email, password, created_at) VALUES (?,?,?, NOW())",
				user.getName(), user.getEmail(), user.getPassword());
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateImage(User user, String image) {
		jdbcTemplate.update("UPDATE users set image_path = ? WHERE email = ? ", image, user.getEmail());
	}

	@Override
	public User findByEmail(String email) {
		return jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?",
				BeanPropertyRowMapper.newInstance(User.class), email);
	}

	@Override
	public int addRole(Role role, User user) {
		return jdbcTemplate.update("INSERT INTO role_user(user_id, role_id) ",
				user.getId(), role.getId());
	}

	@Override
	public List<Role> getUserRoles(User user) {
		return (List<Role>) jdbcTemplate.queryForObject(
				"SELECT roles.id, roles.name FROM role_user LEFT JOIN roles ON role_id = roles.id " +
						"WHERE user_id = ?",
				new Object[] { user.getId() },
				BeanPropertyRowMapper.newInstance(Role.class)
		);
	}
}
