package com.murebackend.murebackend.User;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.murebackend.murebackend.Role.Role;

@Repository
public class JdbcUserRepository implements UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Long save(User user, String password) {
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(connection -> {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO users (name, email, password, created_at) VALUES (?,?,?, NOW())",
							new String[] { "id" });
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, password);
			return ps;
		}, keyHolder);


		return keyHolder.getKey().longValue();
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
	public int addRole(Long roleId, Long userId) {
		return jdbcTemplate.update("INSERT INTO role_user(user_id, role_id) VALUES(?, ?) ",
				userId, roleId);
	}

	@Override
	public List<Role> getUserRoles(User user) {
		return jdbcTemplate.query(
				"SELECT roles.id, roles.name FROM role_user LEFT JOIN roles ON role_id = roles.id " +
						"WHERE user_id = ?",
				BeanPropertyRowMapper.newInstance(Role.class),
				user.getId());
	}
}
